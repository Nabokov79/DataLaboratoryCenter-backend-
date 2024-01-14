package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.ObjectsType;

public interface ObjectsTypeRepository extends JpaRepository<ObjectsType, Long> {
}