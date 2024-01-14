package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.models.ReportTemplate;
import ru.nabokovsg.templates.models.SectionTemplate;

import java.util.Set;

public interface ReportTemplateRepository extends JpaRepository<ReportTemplate, Long> {

    @Query("select r.pageTitle from ReportTemplate r")
    Set<PageTitleTemplate> findAllPageTitle();

    @Query("select r.sections from ReportTemplate r where r.id = ?1")
    Set<SectionTemplate> findAllSections(Long id);
}