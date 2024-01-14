package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.templates.models.ColumnHeaderTemplate;

import java.util.List;
import java.util.Set;

public interface ColumnHeaderTemplateRepository extends JpaRepository<ColumnHeaderTemplate, Long> {

    @Query("select c from ColumnHeaderTemplate c where c.heading in :headings")
    Set<ColumnHeaderTemplate> findAllByCellName(@Param("headings") List<String> headings);
}