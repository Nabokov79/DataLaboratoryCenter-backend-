package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.templates.models.MeasuringToolTemplate;

import java.util.List;
import java.util.Set;

public interface MeasuringToolDataRepository extends JpaRepository<MeasuringToolTemplate, Long> {

    @Query("select m from MeasuringToolTemplate m where m.value in :value")
    Set<MeasuringToolTemplate> findAllByValue(@Param("value") List<String> value);
}