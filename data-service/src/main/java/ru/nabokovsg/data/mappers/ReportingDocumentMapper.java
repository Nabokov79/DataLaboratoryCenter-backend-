package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.data.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.data.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.data.dto.reportingDocument.UpdateReportingDocumentDto;
import ru.nabokovsg.data.models.ReportingDocument;
import ru.nabokovsg.data.models.enums.DocumentType;

@Mapper(componentModel = "spring")
public interface ReportingDocumentMapper {


    @Mapping(source = "documentDto.title", target = "title")
    @Mapping(source = "documentDto.heading", target = "heading")
    @Mapping(source = "documentType", target = "documentType")
    @Mapping(target = "id", ignore = true)
    ReportingDocument mapToNewReportingDocument(DocumentType documentType, NewReportingDocumentDto documentDto);

    @Mapping(source = "documentDto.title", target = "title")
    @Mapping(source = "documentDto.heading", target = "heading")
    @Mapping(source = "document.documentType", target = "documentType")
    @Mapping(source = "document.id", target = "id")
    ReportingDocument mapToUpdateReportingDocument(ReportingDocument document, UpdateReportingDocumentDto documentDto);

    ReportingDocumentDto mapToReportingDocumentDto(ReportingDocument reportingDocument);
}