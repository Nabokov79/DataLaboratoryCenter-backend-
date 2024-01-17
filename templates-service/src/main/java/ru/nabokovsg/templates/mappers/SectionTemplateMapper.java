package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.nabokovsg.templates.dto.section.NewSectionTemplateDto;
import ru.nabokovsg.templates.dto.section.SectionTemplateDto;
import ru.nabokovsg.templates.dto.section.UpdateSectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.ShortSubsectionTemplateDto;
import ru.nabokovsg.templates.models.SectionTemplate;
import ru.nabokovsg.templates.models.SubsectionTemplate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SectionTemplateMapper {

    SectionTemplate mapToNewSectionTemplate(NewSectionTemplateDto sectionDto);

    SectionTemplate mapToUpdateSectionTemplate(@MappingTarget SectionTemplate sectionTemplate
                                                            , UpdateSectionTemplateDto sectionDto);

    SectionTemplateDto mapToSectionTemplateDto(SectionTemplate section);

    ShortSubsectionTemplateDto mapToShortSubsectionTemplateDto(SubsectionTemplate subsection);
}