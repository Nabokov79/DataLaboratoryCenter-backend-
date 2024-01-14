package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.ProtocolTemplate;

public interface ProtocolTemplateRepository extends JpaRepository<ProtocolTemplate, Long> {

     ProtocolTemplate findByReportingDocumentIdAndObjectTypeId(Long reportingDocumentId, Long objectTypeId);
}