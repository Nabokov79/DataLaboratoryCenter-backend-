package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.data.models.SizeParameters;

import java.util.List;
import java.util.Set;

public interface DefectParameterRepository extends JpaRepository<SizeParameters, Long> {

    @Query("select p from SizeParameters p where p.parametersName in :parametersNames")
    Set<SizeParameters> findAllDefectParameterByParametersName(@Param("parametersNames") List<String> parametersNames);
}