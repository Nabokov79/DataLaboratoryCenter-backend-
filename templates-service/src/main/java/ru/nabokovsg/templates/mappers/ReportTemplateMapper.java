package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.templates.dto.pageTitle.ShortPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.dto.section.ShortSectionTemplateDto;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.models.ReportTemplate;
import ru.nabokovsg.templates.models.SectionTemplate;

@Mapper(componentModel = "spring")
public interface ReportTemplateMapper {

    @Mapping(source = "pageTitle", target = "pageTitle")
    @Mapping(target = "id", ignore = true)
    ReportTemplate mapToNewReportTemplate(PageTitleTemplate pageTitle);

    ReportTemplateDto mapToReportTemplateDto(ReportTemplate reportTemplate);

    ShortSectionTemplateDto mapToShortSectionTemplateDto(SectionTemplate section);

    ShortPageTitleTemplateDto mapToShortPageTitleTemplateDto(PageTitleTemplate pageTitle);
}