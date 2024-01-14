package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.data.models.RepairMethod;

import java.util.List;
import java.util.Set;

public interface RepairMethodRepository extends JpaRepository<RepairMethod, Long> {

    @Query("select r from RepairMethod r where r.methodName in :methodNames")
    Set<RepairMethod> findAllByMethodName(@Param("methodNames") List<String> methodNames);
}