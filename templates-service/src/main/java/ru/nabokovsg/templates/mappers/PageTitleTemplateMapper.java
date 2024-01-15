package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.nabokovsg.templates.dto.clientDto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.UpdatePageTitleTemplateDto;
import ru.nabokovsg.templates.models.HeaderTemplate;
import ru.nabokovsg.templates.models.PageTitleTemplate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PageTitleTemplateMapper {

    @Mapping(source = "header", target = "header")
    @Mapping(target = "id", ignore = true)
    PageTitleTemplate mapToNewPageTitleTemplate(NewPageTitleTemplateDto pageTitleDto, HeaderTemplate header);

    @Mapping(source = "header", target = "header")
    @Mapping(source = "pageTitleDto.id", target = "id")
    PageTitleTemplate mapToUpdatePageTitleTemplate(UpdatePageTitleTemplateDto pageTitleDto, HeaderTemplate header);

    PageTitleTemplateDto mapToPageTitleTemplateDto(PageTitleTemplate pageTitle);

    @Mapping(source = "reportingDocumentDto.title", target = "title")
    @Mapping(source = "reportingDocumentDto.heading", target = "heading")
    @Mapping(source = "signature", target = "signature")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "pageTitle.id", target = "id")
    PageTitleTemplate mapToPageTitleTemplate(PageTitleTemplate pageTitle
                                           , ReportingDocumentDto reportingDocumentDto
                                           , String signature
                                           , String year);
}