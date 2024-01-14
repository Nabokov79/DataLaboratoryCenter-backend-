package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.department.DepartmentDto;
import ru.nabokovsg.data.dto.department.NewDepartmentDto;
import ru.nabokovsg.data.dto.department.ShortDepartmentDto;
import ru.nabokovsg.data.dto.department.UpdateDepartmentDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.BranchMapper;
import ru.nabokovsg.data.mappers.DepartmentMapper;
import ru.nabokovsg.data.models.Department;
import ru.nabokovsg.data.models.Licenses;
import ru.nabokovsg.data.repository.DepartmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;
    private final ContactService contactService;
    private final AddressService addressService;
    private final BranchService branchService;
    private final BranchMapper branchMapper;

    @Override
    public DepartmentDto save(NewDepartmentDto departmentDto) {
        Department department = repository.findByDepartment(departmentDto.getDepartment());
        if (department != null) {
            return mapper.mapToDepartmentDto(department);
        }
        department = mapper.mapToNewDepartment(departmentDto);
        if(departmentDto.getAddressId() != null) {
            department.setAddress(addressService.get(departmentDto.getAddressId()));
        }
        department.setBranch(branchMapper.mapToBranch(branchService.get(departmentDto.getBranchId())));
        if (departmentDto.getContact() != null) {
            department.setContact(contactService.save(departmentDto.getContact()));
        }
        return mapper.mapToDepartmentDto(repository.save(department));
    }

    @Override
    public DepartmentDto update(UpdateDepartmentDto departmentDto) {
        Department department = mapper.mapToUpdateDepartment(departmentDto);
        if(departmentDto.getAddressId() != null) {
            department.setAddress(addressService.get(departmentDto.getAddressId()));
        }
        department.setBranch(branchMapper.mapToBranch(branchService.get(departmentDto.getBranchId())));
        if (departmentDto.getContact() != null) {
            department.setContact(contactService.update(departmentDto.getContact()));
        }
        return mapper.mapToDepartmentDto(repository.save(department));
    }

    @Override
    public DepartmentDto get(Long id) {
        return mapper.mapToDepartmentDto(getById(id));
    }

    @Override
    public List<ShortDepartmentDto> getAll(Long branchId) {
        return repository.findByBranch(branchMapper.mapToBranch(branchService.get(branchId)))
                         .stream()
                         .map(mapper::mapToShortDepartmentDto)
                         .toList();
    }

    @Override
    public void addLicense(Long id, Licenses license) {
        Department department = getById(id);
        department.getLicenses().add(license);
        repository.save(department);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Department with id=%s not found for delete.", id));
    }

    private Department getById(Long id) {
        return repository.findById(id).orElseThrow(
                        () -> new NotFoundException(String.format("Department with id=%s not found", id)));
    }
}