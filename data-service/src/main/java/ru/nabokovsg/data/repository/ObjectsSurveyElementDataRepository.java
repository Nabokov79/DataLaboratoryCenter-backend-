package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.data.models.ElementRejectionParameters;
import ru.nabokovsg.data.models.SurveyObjectElementData;

public interface ObjectsSurveyElementDataRepository extends JpaRepository<SurveyObjectElementData, Long> {

    SurveyObjectElementData findByElementId(Long elementId);
}