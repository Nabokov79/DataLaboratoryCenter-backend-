package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.AutoDataCollection;

public interface AutoDataCollectionRepository extends JpaRepository<AutoDataCollection, Long> {
}