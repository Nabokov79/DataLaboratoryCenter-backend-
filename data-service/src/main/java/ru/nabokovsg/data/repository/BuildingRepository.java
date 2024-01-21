package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.data.models.Building;
import ru.nabokovsg.data.models.Department;

import java.util.List;
import java.util.Set;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    Set<Building> findAllByDepartment(Department department);

    @Query("select b from Building b where b.address.id in :ids")
    Set<Building> findAllByAddressId(@Param("ids") List<Long> ids);
}