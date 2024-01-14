package ru.nabokovsg.data.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.reportingDocumentData.*;
import ru.nabokovsg.data.exceptions.BadRequestException;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.ReportingDocumentDataMapper;
import ru.nabokovsg.data.models.Application;
import ru.nabokovsg.data.models.Employee;
import ru.nabokovsg.data.models.QReportingDocumentData;
import ru.nabokovsg.data.models.ReportingDocumentData;
import ru.nabokovsg.data.models.enums.Status;
import ru.nabokovsg.data.repository.ReportingDocumentDataRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportingDocumentDataServiceImpl implements ReportingDocumentDataService {

    private final ReportingDocumentDataRepository repository;
    private final ReportingDocumentDataMapper mapper;
    private final EntityManager entityManager;
    private final EmployeeService employeeService;

    @Override
    public void save(List<Application> applications) {
        List<ReportingDocumentData> documents = repository.findAllByApplication(applications);
        if (!applications.isEmpty() && documents.isEmpty()) {
            Integer number = getLargestDocumentNumber();
            for (Application application : applications) {
                ReportingDocumentData document = mapper.mapToReportingDocumentData(application);
                document.setApplication(application);
                document.setDocumentStatus(Status.WAITING);
                document.setDrawingStatus(Status.WAITING);
                if (number == null) {
                    number = 0;
                }
                document.setDocumentNumber(++number);
                documents.add(document);
            }
           try {
               repository.saveAll(documents);
           } catch (RuntimeException e) {
               log.error("ReportingDocumentData: RuntimeException message={}", e.getMessage());
           }
        }
    }

    @Override
    public List<ReportingDocumentDataDto> update(List<UpdateReportingDocumentDataDto> dataDto) {
        validateIds(dataDto.stream().map(UpdateReportingDocumentDataDto::getId).toList());
        Map<Long, Employee> employees = employeeService.getAllById(
                Stream.of(dataDto.stream().map(UpdateReportingDocumentDataDto::getEmployeeDocumentId)
                                        , dataDto.stream().map(UpdateReportingDocumentDataDto::getEmployeeDrawingId)
                                        , dataDto.stream().map(UpdateReportingDocumentDataDto::getEmployeeDocumentId))
                                        .flatMap(Function.identity())
                                                .distinct()
                                                .toList())
                                        .stream()
                                        .collect(Collectors.toMap(Employee::getId, e -> e));
        return getListDto(dataDto.stream().map(d -> {
                    ReportingDocumentData documentData = mapper.mapToUpdateReportingDocumentData(d);
                    if (d.getEmployeeDocumentId() != null) {
                        documentData.setEmployeeDocument(employees.get(d.getEmployeeDocumentId()));
                    }
                    if (d.getEmployeeDrawingId()!= null) {
                        documentData.setEmployeeDrawing(employees.get(d.getEmployeeDrawingId()));
                    }
                    return documentData;
                })
                .toList());
    }

    @Override
    public List<ReportingDocumentDataDto> getAll(ReportingDocumentDataSearchParametersDto parameters) {
        QReportingDocumentData reporting = QReportingDocumentData.reportingDocumentData;
        List<ReportingDocumentData> data =  new JPAQueryFactory(entityManager).from(reporting)
                                                                              .select(reporting)
                                                                              .where(getPredicate(parameters))
                                                                              .fetch();
        if (data.isEmpty()) {
            data = repository.findAll();
        }
        return getListDto(data);
    }

    @Override
    public void saveDocumentData(DocumentDataDto dataDto) {
        ReportingDocumentData data = getById(dataDto.getId());
        data.setDocumentPath(dataDto.getPath());
        data.setEmployeeDocument(employeeService.getById(dataDto.getEmployeeId()));
        if (data.getApplication().getNeedDrawing()) {
           if (data.getDrawingStatus().equals(Status.COMPLETED)) {
               data.setDocumentStatus(Status.CHECK);
           } else {
               data.setDocumentStatus(Status.COMPLETED);
           }
        } else {
            data.setDocumentStatus(Status.CHECK);
        }
        repository.save(data);
    }

    @Override
    public void saveDrawingData(DrawingDataDto dataDto) {
        ReportingDocumentData data = getById(dataDto.getId());
        data.setDrawingPath(dataDto.getPath());
        data.setEmployeeDrawing(employeeService.getById(dataDto.getEmployeeId()));
        data.setDrawingStatus(Status.COMPLETED);
        if (data.getDocumentStatus().equals(Status.COMPLETED)) {
            data.setDocumentStatus(Status.CHECK);
        }
        repository.save(data);
    }

    @Override
    public ReportingDocumentData getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Reporting document data with id=%s not found", id))
                );
    }

    private Integer getLargestDocumentNumber() {
        LocalDate now = LocalDate.now();
        QReportingDocumentData data = QReportingDocumentData.reportingDocumentData;
        return new JPAQueryFactory(entityManager)
                .from(data)
                .select(data.documentNumber.max())
                .where(QReportingDocumentData.reportingDocumentData.application.date.after(now.with(firstDayOfYear()))
                .and(QReportingDocumentData.reportingDocumentData.application.date.before(now.with(lastDayOfYear()))))
                .fetchOne();
    }

    private void validateIds(List<Long> ids) {
        Map<Long, ReportingDocumentData> data = repository.findAllById((ids))
                                                       .stream()
                                                       .collect(Collectors.toMap(ReportingDocumentData::getId, a -> a));
        if (data.size() != ids.size() || data.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(data.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("ReportingDocumentData with ids= %s not found", ids));
        }
    }
    private BooleanBuilder getPredicate(ReportingDocumentDataSearchParametersDto parameters) {
        LocalDate now = LocalDate.now();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(parameters.getSurveyObjectId() != null) {
            booleanBuilder.and(
                 QReportingDocumentData.reportingDocumentData.application.surveyObject.id
                                                                                   .eq(parameters.getSurveyObjectId()));
        }
        if(parameters.getEmployeeDocumentId() != null) {
            booleanBuilder.and(
                    QReportingDocumentData.reportingDocumentData.employeeDocument.id
                            .eq(parameters.getEmployeeDocumentId()));
        }
        if(parameters.getEmployeeDrawingId() != null) {
            booleanBuilder.and(
                    QReportingDocumentData.reportingDocumentData.employeeDrawing.id
                            .eq(parameters.getEmployeeDrawingId()));
        }
        if(parameters.getStartPeriod() != null) {
            booleanBuilder.and(
                    QReportingDocumentData.reportingDocumentData.documentEndDate.after(
                            LocalDateTime.of(parameters.getStartPeriod(), LocalDateTime.now().toLocalTime()))
            );
        } else {
            booleanBuilder.and(
                    QReportingDocumentData.reportingDocumentData.application.date.after(now.with(firstDayOfYear())));
        }
        if(parameters.getEndPeriod() != null) {
            booleanBuilder.and(
                    QReportingDocumentData.reportingDocumentData.documentEndDate.before(
                            LocalDateTime.of(parameters.getEndPeriod(), LocalDateTime.now().toLocalTime()))
            );
        } else {
            booleanBuilder.and(
                    QReportingDocumentData.reportingDocumentData.application.date.before(now.with(lastDayOfYear())));
        }
        if(parameters.getTransferDate() != null) {
            booleanBuilder.and(
                    QReportingDocumentData.reportingDocumentData.transferDate.eq(parameters.getTransferDate()));
        }
        if (parameters.getDocumentStatus() != null) {
            Status status = Status.from(parameters.getDocumentStatus())
                    .orElseThrow(() -> new BadRequestException(
                            String.format("Unknown document status=%s", parameters.getDocumentStatus()))
                    );
            booleanBuilder.and(QReportingDocumentData.reportingDocumentData.documentStatus.eq(status));
        }
        if (parameters.getDrawingStatus() != null) {
            Status status = Status.from(parameters.getDocumentStatus())
                    .orElseThrow(() -> new BadRequestException(
                            String.format("Unknown drawing status=%s", parameters.getDrawingStatus()))
                    );
            booleanBuilder.and(QReportingDocumentData.reportingDocumentData.documentStatus.eq(status));
        }
        return booleanBuilder;
    }

    private List<ReportingDocumentDataDto> getListDto(List<ReportingDocumentData> reportingDocumentData) {
        return reportingDocumentData.stream()
                                    .map(mapper::mapToReportingDocumentDataDto)
                                    .toList();
    }
}