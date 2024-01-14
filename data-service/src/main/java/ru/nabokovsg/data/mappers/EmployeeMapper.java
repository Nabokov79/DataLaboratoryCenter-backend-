package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.employee.EmployeeDto;
import ru.nabokovsg.data.dto.employee.NewEmployeeDto;
import ru.nabokovsg.data.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.data.dto.employee.UpdateEmployeeDto;
import ru.nabokovsg.data.models.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee mapToEmployee(NewEmployeeDto employeeDto);

    EmployeeDto mapToEmployeeDto(Employee employee);

    Employee mapToUpdateEmployee(UpdateEmployeeDto employeeDto);

    ShortEmployeeDto mapToEmployeeShortDto(Employee employee);
}