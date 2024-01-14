package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.section.NewSectionTemplateDto;
import ru.nabokovsg.templates.dto.section.SectionTemplateDto;
import ru.nabokovsg.templates.dto.section.UpdateSectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.ShortSubsectionTemplateDto;
import ru.nabokovsg.templates.models.SectionTemplate;
import ru.nabokovsg.templates.models.SubsectionTemplate;

@Mapper(componentModel = "spring")
public interface SectionTemplateMapper {

    SectionTemplate mapToNewSectionTemplate(NewSectionTemplateDto sectionDto);

    SectionTemplate mapToUpdateSectionTemplate(UpdateSectionTemplateDto sectionDto);

    SectionTemplateDto mapToSectionTemplateDto(SectionTemplate section);

    ShortSubsectionTemplateDto mapToShortSubsectionTemplateDto(SubsectionTemplate subsection);
}