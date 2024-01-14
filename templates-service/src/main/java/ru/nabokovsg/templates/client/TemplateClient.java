package ru.nabokovsg.templates.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.clientDto.*;

@Service
@RequiredArgsConstructor
public class TemplateClient {

    private final DataServiceClient client;

    public OrganizationDto getOrganization(Long id) {
        return client.getOrganization("/data/organizations/" + id);
    }

    public BranchDto getBranch(Long id) {
        return client.getBranch("/data/organizations/branch/" + id);
    }

    public DepartmentDto getDepartment(Long id) {
        return client.getDepartment("/data/organizations/branch/department/" + id);
    }

    public ReportingDocumentDto getReportingDocument(Long id) {
        return client.getReportingDocument("/data/reporting/document/" + id);
    }

    public ObjectsTypeDto getObjectsType(Long id) {
        return client.getObjectsType("/data/objects/type/" + id);
    }

    public ShortEmployeeDto getShortEmployee(Long id) {
        return client.getShortEmployee("/data/employee/" + id);
    }

    public EmployeeDto getEmployee(Long id) {
        return client.getEmployee("/data/employee/" + id);
    }
}