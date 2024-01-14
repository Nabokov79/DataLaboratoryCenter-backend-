package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.pageTitle.ShortPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.dto.section.ShortSectionTemplateDto;
import ru.nabokovsg.templates.models.AppendicesTemplate;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.models.SectionTemplate;

import java.util.List;

public interface ReportTemplateService {

    ReportTemplateDto get(Long id, Long objectTypeId, Long reportingDocumentId);

    List<ShortPageTitleTemplateDto> getAll();

    List<ShortSectionTemplateDto> getAllSections(Long id);

    void saveWithPageTitleTemplate(PageTitleTemplate pageTitle);

    void saveWithSectionTemplate(Long reportId, List<SectionTemplate> sections);

    List<SectionTemplate> existsSubsectionsByReportId(Long id);

    void addAppendices(Long id, AppendicesTemplate appendices);
}