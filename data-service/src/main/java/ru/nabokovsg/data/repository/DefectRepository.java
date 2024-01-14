package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.data.models.Defect;

import java.util.List;
import java.util.Set;

public interface DefectRepository extends JpaRepository<Defect, Long> {

    @Query("select d from Defect d where d.defectName in :names")
    Set<Defect> findDefectParameterByParametersName(@Param("names") List<String> names);
}