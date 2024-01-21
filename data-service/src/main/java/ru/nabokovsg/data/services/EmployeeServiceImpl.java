package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.employee.EmployeeDto;
import ru.nabokovsg.data.dto.employee.NewEmployeeDto;
import ru.nabokovsg.data.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.data.dto.employee.UpdateEmployeeDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.*;
import ru.nabokovsg.data.models.*;
import ru.nabokovsg.data.repository.CertificateRepository;
import ru.nabokovsg.data.repository.EmployeeRepository;
import ru.nabokovsg.data.repository.MeasuringToolRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final CertificateRepository certificateRepository;
    private final MeasuringToolRepository measuringToolRepository;
    private final ContactService contactService;
    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;
    private final BranchService branchService;
    private final BranchMapper branchMapper;
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @Override
    public ShortEmployeeDto save(NewEmployeeDto employeeDto) {
        Employee employee = repository.findByNameAndPatronymicAndSurname(employeeDto.getName()
                                                                       , employeeDto.getPatronymic()
                                                                       , employeeDto.getSurname());
        if (employee == null) {
            employee = mapper.mapToNewEmployee(employeeDto
                                             , getOrganization(employeeDto.getOrganizationId())
                                             , getBranch(employeeDto.getBranchId())
                                             , getDepartment(employeeDto.getDepartmentId()));
            if (employeeDto.getContact() != null) {
                employee.setContact(contactService.save(employeeDto.getContact()));
            }
        }
        return mapper.mapToEmployeeShortDto(repository.save(employee));
    }

    @Override
    public ShortEmployeeDto update(UpdateEmployeeDto employeeDto) {
        if (!repository.existsById(employeeDto.getId())) {
            throw new NotFoundException(String.format("employee with id=%s not found for update",employeeDto.getId()));
        }
        Employee employee = mapper.mapToUpdateEmployee(employeeDto
                                                     , getOrganization(employeeDto.getOrganizationId())
                                                     , getBranch(employeeDto.getBranchId())
                                                     , getDepartment(employeeDto.getDepartmentId()));
        if (employeeDto.getContact() != null) {
            employee.setContact(contactService.update(employeeDto.getContact()));
        }
        return mapper.mapToEmployeeShortDto(repository.save(employee));
    }

    @Override
    public EmployeeDto get(Long id) {
        return mapper.mapToEmployeeDto(getById(id));
    }

    @Override
    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("employee with id=%s was not found", id)));
    }

    @Override
    public List<ShortEmployeeDto> getAll() {
        return repository.findAll().stream().map(mapper::mapToEmployeeShortDto).collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            updateMeasuringTool(id);
            certificateRepository.deleteAllByEmployeeId(id);
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("employee with id = %s not found for delete",id));
    }

    private void updateMeasuringTool(Long id) {
        Set<MeasuringTool> measuringTools = measuringToolRepository.findAllByEmployeeId(id);
        measuringTools.forEach(measuringTool -> measuringTool.setEmployee(null));
        measuringToolRepository.saveAll(measuringTools);
    }

    private Organization getOrganization(Long organizationId) {
        return organizationMapper.mapToOrganization(organizationService.get(organizationId));
    }

    private Branch getBranch(Long branchId) {
        return branchMapper.mapToBranch(branchService.get(branchId));
    }

    private Department getDepartment(Long departmentId) {
        return departmentMapper.mapToDepartment(departmentService.get(departmentId));
    }
}