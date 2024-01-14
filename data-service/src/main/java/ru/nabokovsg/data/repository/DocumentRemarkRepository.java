package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.DocumentRemark;

public interface DocumentRemarkRepository extends JpaRepository<DocumentRemark, Long> {
}