package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.dto.clientDto.BranchDto;
import ru.nabokovsg.templates.dto.clientDto.DepartmentDto;
import ru.nabokovsg.templates.dto.clientDto.OrganizationDto;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDataDto;
import ru.nabokovsg.templates.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.UpdateHeaderTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.HeaderTemplateMapper;
import ru.nabokovsg.templates.models.HeaderTemplate;
import ru.nabokovsg.templates.repository.HeaderTemplateRepository;
import ru.nabokovsg.templates.services.builders.StringBuilderService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeaderTemplateServiceImpl implements HeaderTemplateService {

    private final HeaderTemplateRepository repository;
    private final HeaderTemplateMapper mapper;
    private final TemplateClient client;
    private final StringBuilderService stringBuilder;

    @Override
    public HeaderTemplate save(NewHeaderTemplateDto headerDto) {
        return repository.save(set(mapper.mapToNewHeaderTemplateData(headerDto)));
    }

    @Override
    public HeaderTemplate update(UpdateHeaderTemplateDto headerDto) {
        if (repository.existsById(headerDto.getId())) {
            return repository.save(
                    mapper.mapToUpdateHeaderTemplate(
                            set(mapper.mapToUpdateHeaderTemplateDataDto(headerDto)), headerDto.getId()
                    )
            );
        }
        throw new NotFoundException(
                String.format("Header template with id=%s not found for update", headerDto.getId())
        );
    }

    private HeaderTemplate set(HeaderTemplateDataDto headerData) {
        OrganizationDto organization = client.getOrganization(headerData.getOrganizationId());
        return getHeaderByDepartmentData(getHeaderByBranchData(getHeaderByOrganizationData(organization, headerData),
                        organization.getBranches()
                                .stream()
                                .collect(Collectors.toMap(BranchDto::getId, b -> b))
                                .get(headerData.getBranchId())
                        , headerData),
                organization.getBranches()
                        .stream()
                        .map(BranchDto::getDepartments)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toMap(DepartmentDto::getId, d -> d))
                        .get(headerData.getDepartmentId())
                , headerData);
    }

    public HeaderTemplate getHeaderByOrganizationData(OrganizationDto organizationDto, HeaderTemplateDataDto data) {
        String organization = organizationDto.getShortNameOrganization();
        String address = "";
        String license = "";
        String contacts = "";
        if (data.getOrganizationFullName()) {
            organization = organizationDto.getOrganization();
        }
        if (data.getOrganizationAddress()) {
            address = stringBuilder.buildFromAddress(organizationDto.getAddress());
        }
        if (data.getOrganizationLicense()) {
            license = stringBuilder.buildFromLicense(organizationDto.getLicenses());
        }
        if (data.getOrganizationContacts()) {
            contacts = stringBuilder.buildFromContacts(organizationDto.getContact());
        }
        return mapper.mapFromOrganizationData(organization, address, license, contacts);
    }

    public HeaderTemplate getHeaderByBranchData(HeaderTemplate header
                                              , BranchDto branchDto
                                              , HeaderTemplateDataDto data) {
        String branch = branchDto.getShortNameBranch();
        String address = "";
        String license = "";
        String contacts = "";
        if (data.getBranchFullName()) {
            branch = branchDto.getBranch();
        }
        if (data.getBranchAddress()) {
            address = stringBuilder.buildFromAddress(branchDto.getAddress());
        }
        if (data.getBranchLicense()) {
            license = stringBuilder.buildFromLicense(branchDto.getLicenses());
        }
        if (data.getBranchContacts()) {
            contacts = stringBuilder.buildFromContacts(branchDto.getContact());
        }
        return mapper.mapFromBranchData(header, branch, address, license, contacts);
    }

    private HeaderTemplate getHeaderByDepartmentData(HeaderTemplate header
                                                   , DepartmentDto departmentDto
                                                   , HeaderTemplateDataDto data) {
        String department = departmentDto.getShortNameDepartment();
        String address = "";
        String license = "";
        String contacts = "";
        if (data.getDepartmentFullName()) {
            department = departmentDto.getDepartment();
        }
        if (data.getDepartmentAddress()) {
            address = stringBuilder.buildFromAddress(departmentDto.getAddress());
        }
        if (data.getDepartmentLicense()) {
            license = stringBuilder.buildFromLicense(departmentDto.getLicenses());
        }
        if (data.getDepartmentContacts()) {
            contacts = stringBuilder.buildFromContacts(departmentDto.getContact());
        }

        return mapper.mapFromDepartmentData(header, department, address, license, contacts);
    }
}