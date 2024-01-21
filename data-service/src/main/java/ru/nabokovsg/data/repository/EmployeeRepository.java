package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Employee findByNameAndPatronymicAndSurname(String name, String patronymic, String surname);
}