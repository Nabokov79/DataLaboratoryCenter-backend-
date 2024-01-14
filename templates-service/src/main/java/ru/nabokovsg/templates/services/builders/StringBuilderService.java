package ru.nabokovsg.templates.services.builders;

import ru.nabokovsg.templates.dto.clientDto.*;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataParam;

import java.util.List;

public interface StringBuilderService {

    String buildFromDocumentation(DocumentationDto documentations);

    String buildFromLicense(List<LicenseDto> licenses);

    String buildFromContacts(ContactDto contact);

    String buildFromAddress(AddressDto address);

    String buildFromShortEmployee(ShortEmployeeDto employee);

    String buildFromEmployeeCertificate(EmployeeDto employee);

    String buildFromOrganization(OrganizationDto organization, DivisionDataParam param);

    String buildFromBranch(BranchDto branch, DivisionDataParam param);

    String buildFromDepartment(DepartmentDto department, DivisionDataParam param);

    String buildFromObjectsType(ObjectsTypeDto objectType);
}