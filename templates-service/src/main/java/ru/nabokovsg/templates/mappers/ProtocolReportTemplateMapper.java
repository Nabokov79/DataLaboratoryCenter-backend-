package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.templates.dto.clientDto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.protocolReport.NewProtocolReportTemplateDto;
import ru.nabokovsg.templates.dto.protocolReport.ProtocolReportTemplateDto;
import ru.nabokovsg.templates.dto.protocolReport.ShortProtocolReportTemplateDto;
import ru.nabokovsg.templates.dto.protocolReport.UpdateProtocolReportTemplateDto;
import ru.nabokovsg.templates.models.ProtocolReportTemplate;

@Mapper(componentModel = "spring")
public interface ProtocolReportTemplateMapper {

    @Mapping(source = "document.title", target = "title")
    @Mapping(source = "document.heading", target = "heading")
    @Mapping(source = "protocolDto.reportingDocumentId", target = "reportingDocumentId")
    @Mapping(source = "protocolDto.userTextAfterHeading", target = "userTextAfterHeading")
    @Mapping(target = "id", ignore = true)
    ProtocolReportTemplate mapToNewProtocolReportTemplate(NewProtocolReportTemplateDto protocolDto
                                                        , ReportingDocumentDto document);

    @Mapping(source = "document.title", target = "title")
    @Mapping(source = "document.heading", target = "heading")
    @Mapping(source = "protocolDto.reportingDocumentId", target = "reportingDocumentId")
    @Mapping(source = "protocolDto.userTextAfterHeading", target = "userTextAfterHeading")
    @Mapping(target = "id", ignore = true)
    ProtocolReportTemplate mapToUpdateProtocolReportTemplate(@MappingTarget ProtocolReportTemplate protocol
                                                                          , UpdateProtocolReportTemplateDto protocolDto
                                                                          , ReportingDocumentDto document);

    ProtocolReportTemplateDto mapToProtocolReportTemplateDto(ProtocolReportTemplate protocol);

    ShortProtocolReportTemplateDto mapToShortProtocolReportTemplateDto(ProtocolReportTemplate protocol);
}