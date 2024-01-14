package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.SurveyObjectElementData;

public interface ObjectsSurveyElementDataRepository extends JpaRepository<SurveyObjectElementData, Long> {
}