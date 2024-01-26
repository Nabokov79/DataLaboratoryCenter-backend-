package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.data.models.ElementRejectionParameters;

public interface ElementRejectionParametersRepository extends JpaRepository<ElementRejectionParameters, Long> {

    @Query("select s from SurveyObjectElementData s where s.id=?1 and s.subElement.id=?2")
    ElementRejectionParameters findByElementIdAndSubElementId(Long elementId, Long subElementId);

    @Query("select s.rejectionParameters from SurveyObjectElementData s where s.id =?1")
    ElementRejectionParameters findByElementId(Long elementId);
}