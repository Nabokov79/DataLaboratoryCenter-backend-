package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.SurveyObject;

import java.util.Set;

public interface SurveyObjectRepository extends JpaRepository<SurveyObject, Long> {

    Set<SurveyObject> findAllByBuildingId(Long buildingId);
}