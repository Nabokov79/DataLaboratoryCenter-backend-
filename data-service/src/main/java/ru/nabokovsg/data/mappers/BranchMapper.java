package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.branch.BranchDto;
import ru.nabokovsg.data.dto.branch.NewBranchDto;
import ru.nabokovsg.data.dto.branch.ShortBranchDto;
import ru.nabokovsg.data.dto.branch.UpdateBranchDto;
import ru.nabokovsg.data.models.Branch;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    Branch mapToNewBranch(NewBranchDto branchDto);

    Branch mapToUpdateBranch(UpdateBranchDto branchDto);

    BranchDto mapToBranchDto(Branch branch);

    ShortBranchDto mapToShortBranchDto(Branch branch);

    Branch mapToBranch(BranchDto branchDto);
}