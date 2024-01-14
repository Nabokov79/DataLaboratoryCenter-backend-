package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}