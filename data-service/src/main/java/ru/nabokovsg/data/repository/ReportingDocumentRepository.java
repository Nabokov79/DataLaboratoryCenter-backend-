package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.ReportingDocument;

public interface ReportingDocumentRepository extends JpaRepository<ReportingDocument, Long> {
}