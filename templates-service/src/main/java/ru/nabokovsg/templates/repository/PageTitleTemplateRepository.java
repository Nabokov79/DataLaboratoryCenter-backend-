package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.PageTitleTemplate;

public interface PageTitleTemplateRepository extends JpaRepository<PageTitleTemplate, Long> {

    PageTitleTemplate findByObjectTypeIdAndReportingDocumentId(Long objectTypeId, Long reportingDocumentId);
}