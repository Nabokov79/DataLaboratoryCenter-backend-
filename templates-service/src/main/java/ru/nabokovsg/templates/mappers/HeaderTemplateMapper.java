package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDataDto;
import ru.nabokovsg.templates.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.UpdateHeaderTemplateDto;
import ru.nabokovsg.templates.models.HeaderTemplate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HeaderTemplateMapper {

    HeaderTemplateDataDto mapToNewHeaderTemplateData(NewHeaderTemplateDto headerDto);

    HeaderTemplateDataDto mapToUpdateHeaderTemplateDataDto(UpdateHeaderTemplateDto headerDto);

    @Mapping(source = "organization", target = "organization")
    @Mapping(source = "address", target = "organizationAddress")
    @Mapping(source = "license", target = "organizationLicense")
    @Mapping(source = "contacts", target = "organizationContacts")
    @Mapping(target = "id", ignore = true)
    HeaderTemplate mapFromOrganizationData(String organization, String address, String license, String contacts);

    @Mapping(source = "branch", target = "branch")
    @Mapping(source = "address", target = "branchAddress")
    @Mapping(source = "license", target = "branchLicense")
    @Mapping(source = "contacts", target = "branchContacts")
    @Mapping(target = "id", ignore = true)
    HeaderTemplate mapFromBranchData(@MappingTarget HeaderTemplate header
                                                  , String branch
                                                  , String address
                                                  , String license
                                                  , String contacts);

    @Mapping(source = "department", target = "department")
    @Mapping(source = "address", target = "departmentAddress")
    @Mapping(source = "license", target = "departmentLicense")
    @Mapping(source = "contacts", target = "departmentContacts")
    @Mapping(target = "id", ignore = true)
    HeaderTemplate mapFromDepartmentData(@MappingTarget HeaderTemplate header
                                                      , String department
                                                      , String address
                                                      , String license
                                                      , String contacts);

    @Mapping(source = "id", target = "id")
    HeaderTemplate mapToUpdateHeaderTemplate(@MappingTarget HeaderTemplate header, Long id);
}