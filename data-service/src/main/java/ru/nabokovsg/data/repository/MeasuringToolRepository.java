package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.nabokovsg.data.models.MeasuringTool;

import java.util.Set;

public interface MeasuringToolRepository extends JpaRepository<MeasuringTool, Long>,
                                                 QuerydslPredicateExecutor<MeasuringTool> {

    Set<MeasuringTool> findAllByEmployeeId(Long employeeId);
}
