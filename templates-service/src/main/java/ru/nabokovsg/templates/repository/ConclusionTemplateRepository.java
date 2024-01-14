package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.templates.models.ConclusionTemplate;

public interface ConclusionTemplateRepository extends JpaRepository<ConclusionTemplate, Long> {

    @Query("select p.conclusions from ProtocolTemplate p where p.id = ?1")
    ConclusionTemplate findByProtocolId(Long id);

    @Query("select p.conclusions from ProtocolReportTemplate p where p.id = ?1")
    ConclusionTemplate findByProtocolReportId(Long id);
}