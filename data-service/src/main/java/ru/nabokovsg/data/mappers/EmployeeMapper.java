package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.data.dto.employee.EmployeeDto;
import ru.nabokovsg.data.dto.employee.NewEmployeeDto;
import ru.nabokovsg.data.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.data.dto.employee.UpdateEmployeeDto;
import ru.nabokovsg.data.models.Branch;
import ru.nabokovsg.data.models.Department;
import ru.nabokovsg.data.models.Employee;
import ru.nabokovsg.data.models.Organization;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "organization", target = "organization")
    @Mapping(source = "branch", target = "branch")
    @Mapping(source = "department", target = "department")
    @Mapping(target = "contact", ignore = true)
    @Mapping(target = "id", ignore = true)
    Employee mapToNewEmployee(NewEmployeeDto employeeDto
                            , Organization organization
                            , Branch branch
                            , Department department);

    EmployeeDto mapToEmployeeDto(Employee employee);

    @Mapping(source = "organization", target = "organization")
    @Mapping(source = "branch", target = "branch")
    @Mapping(source = "department", target = "department")
    @Mapping(source = "employeeDto.id", target = "id")
    @Mapping(target = "contact", ignore = true)
    Employee mapToUpdateEmployee(UpdateEmployeeDto employeeDto
                               , Organization organization
                               , Branch branch
                               , Department department);

    ShortEmployeeDto mapToEmployeeShortDto(Employee employee);
}