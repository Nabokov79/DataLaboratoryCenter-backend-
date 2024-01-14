package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.templates.models.SubsectionTemplate;

import java.util.Set;

public interface SubsectionTemplateRepository extends JpaRepository<SubsectionTemplate, Long> {

    @Query("select s.subsections from SectionTemplate s where s.id = ?1")
    Set<SubsectionTemplate> findBySection(Long id);

    @Query("select p.subsections from ProtocolTemplate p where p.id = ?1")
    Set<SubsectionTemplate> findByProtocol(Long id);

    @Query("select p.subsections from ProtocolReportTemplate p where p.id = ?1")
    Set<SubsectionTemplate> findByProtocolReport(Long id);
}