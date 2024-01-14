package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.UpdatePageTitleTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.PageTitleTemplateMapper;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.repository.PageTitleTemplateRepository;
import ru.nabokovsg.templates.services.builders.StringBuilderService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PageTitleTemplateServiceImpl implements PageTitleTemplateService {

    private final PageTitleTemplateRepository repository;
    private final PageTitleTemplateMapper mapper;
    private final TemplateClient client;
    private final HeaderTemplateService headerService;
    private final ReportTemplateService reportService;
    private final StringBuilderService stringBuilder;

    @Override
    public PageTitleTemplateDto save(NewPageTitleTemplateDto pageTitleDto) {
        PageTitleTemplate pageTitle = repository.findByObjectTypeIdAndReportingDocumentId(pageTitleDto.getObjectTypeId()
                                                                               , pageTitleDto.getReportingDocumentId());
        if (pageTitle == null) {
            pageTitle = mapper.mapToNewPageTitleTemplate(pageTitleDto, headerService.save(pageTitleDto.getHeader()));
            pageTitle = repository.save(mapper.mapToPageTitleTemplate(
                            pageTitle
                            , client.getReportingDocument(pageTitleDto.getReportingDocumentId())
                            , stringBuilder.buildFromShortEmployee(client.getShortEmployee(pageTitleDto.getEmployeeId()))
                            , String.valueOf((LocalDate.now().getYear()))
                    ));
            reportService.saveWithPageTitleTemplate(pageTitle);
            return mapper.mapToPageTitleTemplateDto(pageTitle);
        }
        return mapper.mapToPageTitleTemplateDto(pageTitle);
    }

    @Override
    public PageTitleTemplateDto update(UpdatePageTitleTemplateDto pageTitleDto) {
        if (repository.existsById(pageTitleDto.getId())) {
            PageTitleTemplate pageTitle = mapper.mapToUpdatePageTitleTemplate(pageTitleDto, headerService.update(pageTitleDto.getHeader()));
            pageTitle = mapper.mapToPageTitleTemplate(
                              pageTitle
                            , client.getReportingDocument(pageTitleDto.getReportingDocumentId())
                            , stringBuilder.buildFromShortEmployee(client.getShortEmployee(pageTitleDto.getEmployeeId()))
                            , String.valueOf((LocalDate.now().getYear())));
            return mapper.mapToPageTitleTemplateDto(repository.save(pageTitle));
        }
        throw new NotFoundException(
                String.format("Page title template with id=%s not found for update", pageTitleDto.getId())
        );
    }
}