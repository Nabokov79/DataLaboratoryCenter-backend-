package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.Branch;
import ru.nabokovsg.data.models.Department;

import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Set<Department> findByBranch(Branch branch);

    Department findByDepartment(String department);
}