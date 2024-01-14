package ru.nabokovsg.data.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.data.models.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    @Modifying
    @Transactional
    @Query("delete from Certificate c where c.employee.id = ?1")
    void deleteAllByEmployeeId(Long employeeId);
}