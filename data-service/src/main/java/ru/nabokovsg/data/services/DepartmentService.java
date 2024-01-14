package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.department.DepartmentDto;
import ru.nabokovsg.data.dto.department.NewDepartmentDto;
import ru.nabokovsg.data.dto.department.ShortDepartmentDto;
import ru.nabokovsg.data.dto.department.UpdateDepartmentDto;
import ru.nabokovsg.data.models.Licenses;

import java.util.List;

public interface DepartmentService {

    DepartmentDto save(NewDepartmentDto departmentDto);

    DepartmentDto update(UpdateDepartmentDto departmentDto);

    DepartmentDto get(Long id);

    List<ShortDepartmentDto> getAll(Long branchId);

    void addLicense(Long id, Licenses license);

    void delete(Long id);
}