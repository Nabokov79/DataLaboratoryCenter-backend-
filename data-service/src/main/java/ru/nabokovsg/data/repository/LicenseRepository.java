package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.Licenses;

public interface LicenseRepository extends JpaRepository<Licenses, Long> {
}