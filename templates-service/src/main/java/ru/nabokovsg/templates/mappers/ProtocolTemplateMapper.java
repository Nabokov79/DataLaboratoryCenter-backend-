package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.nabokovsg.templates.dto.clientDto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.ShortProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.UpdateProtocolTemplateDto;
import ru.nabokovsg.templates.models.CharacteristicsSurveyObject;
import ru.nabokovsg.templates.models.HeaderTemplate;
import ru.nabokovsg.templates.models.ProtocolTemplate;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProtocolTemplateMapper {

    @Mapping(source = "header", target = "header")
    @Mapping(target = "id", ignore = true)
    ProtocolTemplate mapToNewProtocolTemplate(NewProtocolTemplateDto protocolDto, HeaderTemplate header);

    @Mapping(source = "header", target = "header")
    @Mapping(source = "protocolDto.id", target = "id")
    ProtocolTemplate mapToUpdateProtocolTemplate(UpdateProtocolTemplateDto protocolDto, HeaderTemplate header);

    @Mapping(source = "objectType", target = "objectType")
    @Mapping(source = "reportingDocumentDto.title", target = "title")
    @Mapping(source = "reportingDocumentDto.heading", target = "heading")
    @Mapping(source = "characteristics", target = "characteristics")
    @Mapping(source = "protocol.id", target = "id")
    ProtocolTemplate mapToProtocolTemplate(ProtocolTemplate protocol
                                         , String objectType
                                         , ReportingDocumentDto reportingDocumentDto
                                         , List<CharacteristicsSurveyObject> characteristics);

    ShortProtocolTemplateDto mapToShortProtocolTemplateDto(ProtocolTemplate protocolTemplate);

    ProtocolTemplateDto mapToProtocolTemplateDto(ProtocolTemplate protocolTemplate);
}