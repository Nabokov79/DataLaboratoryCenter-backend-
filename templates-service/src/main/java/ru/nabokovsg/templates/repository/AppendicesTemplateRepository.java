package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.templates.models.AppendicesTemplate;

import java.util.Set;

public interface AppendicesTemplateRepository extends JpaRepository<AppendicesTemplate, Long> {

    @Query("select r.appendices from ReportTemplate r where r.id=?1")
    Set<AppendicesTemplate> findByReportId(Long id);

    @Query("select p.appendices from ProtocolTemplate p where p.id=?1")
    Set<AppendicesTemplate> findByProtocolId(Long id);
}