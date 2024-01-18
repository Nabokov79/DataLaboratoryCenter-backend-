package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.templates.models.PageTitleTemplate;

public interface PageTitleTemplateRepository extends JpaRepository<PageTitleTemplate, Long> {

    PageTitleTemplate findByObjectTypeIdAndReportingDocumentId(Long objectTypeId, Long reportingDocumentId);

    @Query("select r.pageTitle.objectTypeId from ReportTemplate r where r.id =?1")
    Long findByReportId(Long reportId);
}