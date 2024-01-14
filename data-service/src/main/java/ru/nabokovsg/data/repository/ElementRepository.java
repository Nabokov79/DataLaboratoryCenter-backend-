package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.data.models.Element;

import java.util.List;
import java.util.Set;

public interface ElementRepository extends JpaRepository<Element, Long> {

    @Query("select e from Element e where e.objectsTypeId in :ids")
    Set<Element> findAllByObjectsTypeId(@Param("ids") List<Long> ids);
}