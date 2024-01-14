package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.employee.EmployeeDto;
import ru.nabokovsg.data.dto.employee.NewEmployeeDto;
import ru.nabokovsg.data.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.data.dto.employee.UpdateEmployeeDto;
import ru.nabokovsg.data.models.Employee;

import java.util.List;

public interface EmployeeService {

    ShortEmployeeDto save(NewEmployeeDto employeeDto);

    ShortEmployeeDto update(UpdateEmployeeDto employeeDto);

    EmployeeDto get(Long id);

    Employee getById(Long id);

    List<ShortEmployeeDto> getAll();

    List<Employee> getAllById(List<Long> ids);

    void delete(Long id);
}
