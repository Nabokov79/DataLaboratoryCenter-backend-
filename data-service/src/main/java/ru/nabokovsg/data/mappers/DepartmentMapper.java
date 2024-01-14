package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.department.DepartmentDto;
import ru.nabokovsg.data.dto.department.NewDepartmentDto;
import ru.nabokovsg.data.dto.department.ShortDepartmentDto;
import ru.nabokovsg.data.dto.department.UpdateDepartmentDto;
import ru.nabokovsg.data.models.Department;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department mapToNewDepartment(NewDepartmentDto departmentDto);

    Department mapToUpdateDepartment(UpdateDepartmentDto departmentDto);

    DepartmentDto mapToDepartmentDto(Department department);

    ShortDepartmentDto mapToShortDepartmentDto(Department department);

    Department mapToDepartment(DepartmentDto departmentDto);
}