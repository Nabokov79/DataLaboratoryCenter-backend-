package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.templates.models.TableTemplate;

import java.util.Set;

public interface TableTemplateRepository extends JpaRepository<TableTemplate, Long> {

    @Query("select s.table from SubsectionTemplate s where s.id = ?1")
    TableTemplate findBySubsection(Long id);

    @Query("select p.tables from ProtocolTemplate p where p.id = ?1")
    Set<TableTemplate> findByProtocol(Long id);

    @Query("select p.tables from ProtocolReportTemplate p where p.id = ?1")
    Set<TableTemplate> findByProtocolReport(Long id);
}