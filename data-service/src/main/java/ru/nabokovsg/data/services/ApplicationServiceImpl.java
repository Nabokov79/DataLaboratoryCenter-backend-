package ru.nabokovsg.data.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.application.ApplicationDto;
import ru.nabokovsg.data.dto.application.ApplicationSearchParametersDto;
import ru.nabokovsg.data.dto.application.NewApplicationDto;
import ru.nabokovsg.data.dto.application.UpdateApplicationDto;
import ru.nabokovsg.data.exceptions.BadRequestException;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.ApplicationMapper;
import ru.nabokovsg.data.models.*;
import ru.nabokovsg.data.models.enums.BuilderType;
import ru.nabokovsg.data.models.enums.Status;
import ru.nabokovsg.data.repository.ApplicationRepository;
import ru.nabokovsg.data.services.builder.DataFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repository;
    private final ApplicationMapper mapper;
    private final EntityManager entityManager;
    private final DataFactory factory;
    private final ReportingDocumentDataService reportingDocumentDataService;

    @Override
    public List<ApplicationDto> save(List<NewApplicationDto> applicationsDto) {
        DataBuilder builder = factory.getBuilder(applicationsDto.stream()
                                                                   .map(mapper::mapFromNewApplicationDto)
                                                                   .toList()
                                                 , BuilderType.APPLICATIONS);
        List<Application> applications = new ArrayList<>();
        for (NewApplicationDto applicationDto : applicationsDto) {
            Application application = set(mapper.mapToNewApplication(applicationDto)
                    , builder
                    , mapper.mapFromNewApplicationDto(applicationDto));
            application.setApplicationStatus(Status.WAITING);
            applications.add(application);
        }
        try {
            applications = repository.saveAll(applications);
            reportingDocumentDataService.save(applications.stream()
                    .filter(a -> a.getDate() != null)
                    .toList());
        } catch (RuntimeException e) {
            log.error("Application: RuntimeException message={}", e.getMessage());
        }
        return getListDto(applications);
    }

    @Override
    public List<ApplicationDto> update(List<UpdateApplicationDto> applicationsDto) {
        validateIds(applicationsDto.stream().map(UpdateApplicationDto::getId).toList());
        DataBuilder builder = factory.getBuilder(applicationsDto.stream()
                                                                .map(mapper::mapFromUpdateApplicationDto)
                                                                .toList()
                                                        , BuilderType.APPLICATIONS);
        List<Application> applications = new ArrayList<>();
        for (UpdateApplicationDto applicationDto : applicationsDto) {
            Application application = set(mapper.mapToUpdateApplication(applicationDto)
                    , builder
                    , mapper.mapFromUpdateApplicationDto(applicationDto));
            application.setApplicationStatus(Status.WAITING);
            applications.add(application);
        }
        try {
            applications = repository.saveAll(applications);
            reportingDocumentDataService.save(applications.stream()
                    .filter(a -> a.getDate() != null)
                    .toList());
        } catch (RuntimeException e) {
            log.error("Application: RuntimeException message={}", e.getMessage());
        }
        return getListDto(applications);
    }

    @Override
    public ApplicationDto get(Long id) {
        return mapper.mapToApplicationDto((repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("application with id=%s not found",id)))));
    }

    @Override
    public List<ApplicationDto> getAll(ApplicationSearchParametersDto parameters) {
        QApplication application = QApplication.application;
        return getListDto(new JPAQueryFactory(entityManager).from(application)
                                                            .select(application)
                                                            .where(getPredicate(parameters)).fetch());
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Application> applications = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Application::getId, a -> a));
        if (applications.size() != ids.size() || applications.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(applications.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("application with ids= %s not found", ids));
        }
    }

    private Application set(Application application, DataBuilder builder, ObjectsIds ids) {
        Map<Long, Branch> branches = builder.getOrganizations().values().stream()
                                                                      .map(Organization::getBranches)
                                                                      .flatMap(Collection::stream)
                                                                      .collect(Collectors.toMap(Branch::getId, b -> b));
        Map<Long, Department> departments = branches.get(ids.getBranchId()).getDepartments()
                                                                  .stream()
                                                                  .collect(Collectors.toMap(Department::getId, d -> d));
        application.setOrganization(builder.getOrganizations().get(ids.getOrganizationId()));
        application.setBranch(branches.get(ids.getBranchId()));
        application.setDepartment(departments.get(ids.getDepartmentId()));
        application.setAddress(builder.getAddresses().get(ids.getAddressId()));
        application.setSurveyObject(builder.getSurveyObjects().get(ids.getSurveyObjectId()));
        application.setEmployees(builder.getEmployees().values()
                                                       .stream()
                                                       .filter(e -> ids.getEmployeeId().equals(e.getId()))
                                                       .toList());
        application.setReportingDocument(builder.getReportingDocuments().get(ids.getReportingDocumentId()));
        return application;
    }

    private BooleanBuilder getPredicate(ApplicationSearchParametersDto parameters) {
        LocalDate now = LocalDate.now();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(parameters.getAddressId() != null) {
            booleanBuilder.and(QApplication.application.address.id.eq(parameters.getAddressId()));
        }
        if(parameters.getStartDatePeriod() != null) {
            booleanBuilder.and(QApplication.application.date.after(parameters.getStartDatePeriod()));
        } else {
            booleanBuilder.and(QApplication.application.date.after(now.with(firstDayOfYear())));
        }
        if(parameters.getEndDatePeriod() != null) {
            booleanBuilder.and(QApplication.application.date.before(parameters.getEndDatePeriod()));
        } else {
            booleanBuilder.and(QApplication.application.date.after(now.with(lastDayOfYear())));
        }
        if(parameters.getSurveyObjectId() != null) {
            booleanBuilder.and(QApplication.application.surveyObject.id.eq(parameters.getSurveyObjectId()));
        }
        if (parameters.getObjectTypeId() != null) {
            booleanBuilder.and(QApplication.application.surveyObject.objectType.id.eq(parameters.getObjectTypeId()));
        }
        if (parameters.getDocumentStatus() != null) {
            Status status = Status.from(parameters.getApplicationStatus())
                    .orElseThrow(() -> new BadRequestException(
                            String.format("Unknown document status=%s", parameters.getApplicationStatus()))
                    );
            booleanBuilder.and(QApplication.application.applicationStatus.eq(status));
        }
        return booleanBuilder;
    }

    private List<ApplicationDto> getListDto(List<Application> applications) {
        return applications.stream().map(mapper::mapToApplicationDto).toList();
    }
}