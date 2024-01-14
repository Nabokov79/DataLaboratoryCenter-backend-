package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.organization.NewOrganizationDto;
import ru.nabokovsg.data.dto.organization.OrganizationDto;
import ru.nabokovsg.data.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.data.dto.organization.UpdateOrganizationDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.OrganizationMapper;
import ru.nabokovsg.data.models.Licenses;
import ru.nabokovsg.data.models.Organization;
import ru.nabokovsg.data.repository.OrganizationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationMapper mapper;
    private final ContactService contactService;
    private final AddressService addressService;

    @Override
    public OrganizationDto save(NewOrganizationDto organizationDto) {
        Organization organizationDb = repository.findByOrganization(organizationDto.getOrganization());
        if (organizationDb!= null) {
            return mapper.mapToOrganizationDto(organizationDb);
        }
        Organization organization = mapper.mapToNewOrganization(organizationDto);
        organization.setAddress(addressService.get(organizationDto.getAddressId()));
        if (organizationDto.getContact() != null) {
            organization.setContact(contactService.save(organizationDto.getContact()));
        }
        return mapper.mapToOrganizationDto(repository.save(organization));
    }

    @Override
    public OrganizationDto update(UpdateOrganizationDto organizationDto) {
        if (repository.existsById(organizationDto.getId())) {
            Organization organization = mapper.mapToUpdateOrganization(organizationDto);
            organization.setAddress(addressService.get(organizationDto.getAddressId()));
            if (organizationDto.getContact() != null) {
                organization.setContact(contactService.update(organizationDto.getContact()));
            }
            return mapper.mapToOrganizationDto(repository.save(mapper.mapToUpdateOrganization(organizationDto)));
        }
        throw new NotFoundException(
                String.format("organization=%s not found for update.", organizationDto.getOrganization()));
    }

    @Override
    public OrganizationDto get(Long id) {
        return mapper.mapToOrganizationDto(getById(id));
    }

    @Override
    public List<ShortOrganizationDto> getAll() {
        return repository.findAllOrganization().stream()
                                               .map(mapper::mapToShortOrganizationDto)
                                               .toList();
    }

    @Override
    public void addLicense(Long id, Licenses license) {
        Organization organization = getById(id);
        organization.getLicenses().add(license);
        repository.save(organization);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
       throw new NotFoundException(String.format("Organization with id=%s not found for delete.", id));
    }

    @Override
    public Organization getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(
                                                    String.format("Organization with id=%s not found for license",id)));
    }
}