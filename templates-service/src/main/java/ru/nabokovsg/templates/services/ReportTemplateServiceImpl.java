package ru.nabokovsg.templates.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.pageTitle.ShortPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.dto.section.ShortSectionTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.ReportTemplateMapper;
import ru.nabokovsg.templates.models.*;
import ru.nabokovsg.templates.repository.ReportTemplateRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final ReportTemplateRepository repository;
    private final ReportTemplateMapper mapper;
    private final EntityManager entityManager;

    @Override
    public ReportTemplateDto get(Long id) {
        return mapper.mapToReportTemplateDto(repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Report template by id=%s not found", id))));
    }

    @Override
    public List<ShortPageTitleTemplateDto> getAll(Long objectTypeId, Long reportingDocumentId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (objectTypeId != null) {
            booleanBuilder.and(QReportTemplate.reportTemplate.pageTitle.objectTypeId.eq(objectTypeId));
        }
        if (reportingDocumentId != null) {
            booleanBuilder.and(QReportTemplate.reportTemplate.pageTitle.reportingDocumentId.eq(reportingDocumentId));
        }
        QReportTemplate report = QReportTemplate.reportTemplate;
        return new JPAQueryFactory(entityManager).from(report)
                                                 .select(report)
                                                 .where(booleanBuilder)
                                                 .fetch()
                                                 .stream()
                                                 .map(ReportTemplate::getPageTitle)
                                                 .map(mapper::mapToShortPageTitleTemplateDto)
                                                 .toList();
    }

    @Override
    public List<ShortSectionTemplateDto> getAllSections(Long id) {
        return repository.findAllSections(id).stream().map(mapper::mapToShortSectionTemplateDto).toList();
    }

    @Override
    public void saveWithPageTitleTemplate(PageTitleTemplate pageTitle) {
        repository.save(mapper.mapToNewReportTemplate(pageTitle));
    }

    @Override
    public void saveWithSectionTemplate(Long reportId, List<SectionTemplate> sections) {
        ReportTemplate template = getById(reportId);
        template.setSections(sections);
        repository.save(template);
    }

    private ReportTemplate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Report template with id=%s not found", id))
                );
    }

    @Override
    public List<SectionTemplate> existsSubsectionsByReportId(Long id) {
        return getById(id).getSections();
    }

    @Override
    public void addAppendices(Long id, AppendicesTemplate appendices) {
        ReportTemplate report = getById(id);
        report.getAppendices().add(appendices);
        repository.save(report);
    }
}