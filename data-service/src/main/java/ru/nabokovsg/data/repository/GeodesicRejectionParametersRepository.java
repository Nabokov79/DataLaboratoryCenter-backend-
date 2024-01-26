package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.data.models.GeodesicRejectionParameters;

import java.util.Set;

public interface GeodesicRejectionParametersRepository extends JpaRepository<GeodesicRejectionParameters, Long> {

    @Query("select s.rejectionParameters from SurveyObject s where s.id=?1")
    Set<GeodesicRejectionParameters> findAllBySurveyObjectId(Long surveyObjectId);
}