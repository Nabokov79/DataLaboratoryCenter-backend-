package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.DataOfSurveyObjectTemplate;


public interface DataOfSurveyObjectTemplateRepository extends JpaRepository<DataOfSurveyObjectTemplate, Long> {
}