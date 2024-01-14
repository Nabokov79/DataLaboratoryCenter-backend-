package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.data.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.data.dto.reportingDocument.UpdateReportingDocumentDto;
import ru.nabokovsg.data.models.enums.DocumentType;

import java.util.List;

public interface ReportingDocumentService {

    List<ReportingDocumentDto> save(DocumentType documentType, List<NewReportingDocumentDto> documentDto);

    List<ReportingDocumentDto> update(List<UpdateReportingDocumentDto> documentDto);

    ReportingDocumentDto get(Long id);

    List<ReportingDocumentDto> getAll();

    void delete(Long id);
}