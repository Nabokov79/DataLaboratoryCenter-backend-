package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.templates.models.SectionTemplate;
import ru.nabokovsg.templates.models.SubsectionTemplate;

import java.util.Set;

public interface SectionTemplateRepository extends JpaRepository<SectionTemplate, Long> {

    @Query("select s.subsections from SectionTemplate s where s.id = ?1")
    Set<SubsectionTemplate> findAllSubsection(Long id);
}