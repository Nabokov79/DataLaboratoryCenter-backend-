package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.organization.NewOrganizationDto;
import ru.nabokovsg.data.dto.organization.OrganizationDto;
import ru.nabokovsg.data.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.data.dto.organization.UpdateOrganizationDto;
import ru.nabokovsg.data.models.Licenses;
import ru.nabokovsg.data.models.Organization;

import java.util.List;

public interface OrganizationService {

    OrganizationDto save(NewOrganizationDto organizationDto);

    OrganizationDto update(UpdateOrganizationDto organizationDto);

    OrganizationDto get(Long id);

    Organization getById(Long id);

    List<ShortOrganizationDto> getAll();

    void addLicense(Long id, Licenses license);

    void delete(Long id);
}