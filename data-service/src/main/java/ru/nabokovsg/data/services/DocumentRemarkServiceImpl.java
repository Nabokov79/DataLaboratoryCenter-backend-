package ru.nabokovsg.data.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.remark.DocumentRemarkDto;
import ru.nabokovsg.data.dto.remark.NewDocumentRemarkDto;
import ru.nabokovsg.data.dto.remark.UpdateDocumentRemarkDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.DocumentRemarkMapper;
import ru.nabokovsg.data.models.DocumentRemark;
import ru.nabokovsg.data.models.Employee;
import ru.nabokovsg.data.models.QDocumentRemark;
import ru.nabokovsg.data.repository.DocumentRemarkRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentRemarkServiceImpl implements DocumentRemarkService {

    private final DocumentRemarkRepository repository;
    private final DocumentRemarkMapper mapper;
    private final ReportingDocumentDataService documentDataService;
    private final EmployeeService employeeService;
    private final EntityManager entityManager;

    @Override
    public DocumentRemarkDto save(NewDocumentRemarkDto remarkDto) {
        DocumentRemark remark = set(mapper.mapToNewDocumentRemark(remarkDto), remarkDto.getEmployeeId());
        remark.setReportingDocumentData(documentDataService.getById(remarkDto.getReportingDocumentDataId()));
        return mapper.mapToDocumentRemarkDto(repository.save(remark));
    }

    @Override
    public DocumentRemarkDto update(UpdateDocumentRemarkDto remarkDto) {
        if (repository.existsById(remarkDto.getId())) {
            DocumentRemark remark = set(mapper.mapToUpdateDocumentRemark(remarkDto), remarkDto.getEmployeeId());
            remark.setReportingDocumentData(documentDataService.getById(remarkDto.getReportingDocumentDataId()));
            return mapper.mapToDocumentRemarkDto(repository.save(remark));
        }
       throw new NotFoundException(String.format("Remark with id=%s not found for update", remarkDto.getId()));
    }

    @Override
    public List<DocumentRemarkDto> getAll(Long employeeId, Long employeeDocumentId, Long employeeDrawingId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (employeeId != null) {
            booleanBuilder.and(
                    QDocumentRemark.documentRemark.employeeDocument.id.eq(employeeId));
        }
        if (employeeDocumentId != null) {
            booleanBuilder.and(
                    QDocumentRemark.documentRemark.reportingDocumentData.employeeDocument.id.eq(employeeDocumentId));
        }
        if (employeeDrawingId != null) {
            booleanBuilder.and(
                    QDocumentRemark.documentRemark.reportingDocumentData.employeeDrawing.id.eq(employeeDrawingId));
        }
        QDocumentRemark remark = QDocumentRemark.documentRemark;
        return new JPAQueryFactory(entityManager)
                                    .from(remark)
                                    .select(remark)
                                    .where(booleanBuilder)
                                    .fetch()
                .stream()
                .map(mapper::mapToDocumentRemarkDto)
                .toList();
        }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Remark with id=%s not found for delete", id));
    }

    private DocumentRemark set(DocumentRemark remark, Long employeeId) {
        Employee employee = employeeService.getById(employeeId);
        if (remark.getDocumentRemarkText() != null) {
            remark.setEmployeeDocument(employee);
        }
        if (remark.getDrawingRemarkText() != null) {
            remark.setEmployeeDrawing(employee);
        }
        return remark;
    }
}