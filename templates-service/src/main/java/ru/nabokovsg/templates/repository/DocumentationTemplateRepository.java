package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.templates.models.DocumentationTemplate;

import java.util.List;
import java.util.Set;

public interface DocumentationTemplateRepository extends JpaRepository<DocumentationTemplate, Long> {

    @Query("select s from DocumentationTemplate s where s.value in :values")
    Set<DocumentationTemplate> findAllByValueDataList(@Param("values") List<String> values);
}