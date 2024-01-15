package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.models.SubsectionTemplate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubsectionTemplateMapper {

    @Mapping(target = "documentation", ignore = true)
    @Mapping(target = "measuringTools", ignore = true)
    @Mapping(target = "id", ignore = true)
    SubsectionTemplate mapToNewSubsectionTemplate(NewSubsectionTemplateDto subsectionDto);

    @Mapping(source = "sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "subsectionName", target = "subsectionName")
    @Mapping(source = "sequentialNumberVisible", target = "sequentialNumberVisible")
    SubsectionTemplate mapToUpdateSubsectionTemplate(@MappingTarget SubsectionTemplate subsectionDb
                                                                  , Double sequentialNumber
                                                                  , String subsectionName
                                                                  , boolean sequentialNumberVisible);

    SubsectionTemplateDto mapToSubsectionTemplateDto(SubsectionTemplate subsection);
}