package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.branch.BranchDto;
import ru.nabokovsg.data.dto.branch.NewBranchDto;
import ru.nabokovsg.data.dto.branch.ShortBranchDto;
import ru.nabokovsg.data.dto.branch.UpdateBranchDto;
import ru.nabokovsg.data.exceptions.BadRequestException;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.BranchMapper;
import ru.nabokovsg.data.models.Branch;
import ru.nabokovsg.data.models.Licenses;
import ru.nabokovsg.data.repository.BranchRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository repository;
    private final BranchMapper mapper;
    private final OrganizationService organizationService;
    private final ContactService contactService;
    private final AddressService addressService;

    @Override
    public BranchDto save(NewBranchDto branchDto) {
        Branch branch = repository.findByBranch(branchDto.getBranch());
        if (branch != null) {
            return mapper.mapToBranchDto(branch);
        }
        branch = mapper.mapToNewBranch(branchDto);
        branch.setAddress(addressService.get(branchDto.getAddressId()));
        branch.setOrganization(organizationService.getById(branchDto.getOrganizationId()));
        if (branchDto.getContact() != null) {
            branch.setContact(contactService.save(branchDto.getContact()));
        }
        return mapper.mapToBranchDto(repository.save(branch));
    }

    @Override
    public BranchDto update(UpdateBranchDto branchDto) {
        if (repository.existsById(branchDto.getId())) {
            Branch branch = mapper.mapToUpdateBranch(branchDto);
            branch.setOrganization(organizationService.getById(branchDto.getOrganizationId()));
            branch.setAddress(addressService.get(branchDto.getAddressId()));
            if (branchDto.getContact() != null) {
                branch.setContact(contactService.update(branchDto.getContact()));
            }
            return mapper.mapToBranchDto(repository.save(branch));
        }
        throw new BadRequestException(String.format("Branch wth id=%s not found for update", branchDto.getId()));
    }

    @Override
    public BranchDto get(Long id) {
        Branch branch = getById(id);
        branch.setDepartments(branch.getDepartments()
              .stream()
              .filter(d -> d.getSupplyArea() != null)
              .sorted((d1, d2) -> d1.getSupplyArea().compareToIgnoreCase(d2.getSupplyArea()))
              .toList());
        return mapper.mapToBranchDto(branch);
    }

    @Override
    public List<ShortBranchDto> getAll(Long organizationId) {
        return repository.findAllByOrganization(organizationService.getById(organizationId))
                         .stream()
                         .map(mapper::mapToShortBranchDto)
                         .toList();
    }

    @Override
    public void addLicense(Long id, Licenses license) {
        Branch branch = getById(id);
        branch.getLicenses().add(license);
        repository.save(branch);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Branch wth id=%s not found for delete", id));
    }

    private Branch getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Branch wth id=%s not found", id)));

    }
}