package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.branch.BranchDto;
import ru.nabokovsg.data.dto.branch.NewBranchDto;
import ru.nabokovsg.data.dto.branch.ShortBranchDto;
import ru.nabokovsg.data.dto.branch.UpdateBranchDto;
import ru.nabokovsg.data.models.Licenses;

import java.util.List;

public interface BranchService {

    BranchDto save(NewBranchDto branchDto);

    BranchDto update(UpdateBranchDto branchDto);

    BranchDto get(Long id);

    List<ShortBranchDto> getAll(Long organizationId);

    void addLicense(Long id, Licenses license);

    void  delete(Long id);
}