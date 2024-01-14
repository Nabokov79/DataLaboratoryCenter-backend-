package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.templates.models.CharacteristicsSurveyObject;

import java.util.List;
import java.util.Set;

public interface CharacteristicsSurveyObjectRepository extends JpaRepository<CharacteristicsSurveyObject, Long> {

    @Query("select c from CharacteristicsSurveyObject c where c.characteristicName in :names")
    Set<CharacteristicsSurveyObject> findAll(@Param("names") List<String> names);
}