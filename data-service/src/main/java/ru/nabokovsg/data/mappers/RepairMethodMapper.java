package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.repairMethod.NewRepairMethodDto;
import ru.nabokovsg.data.dto.repairMethod.RepairMethodDto;
import ru.nabokovsg.data.dto.repairMethod.UpdateRepairMethodDto;
import ru.nabokovsg.data.models.RepairMethod;

@Mapper(componentModel = "spring")
public interface RepairMethodMapper {

    RepairMethod mapToNewRepairMethod(NewRepairMethodDto methodDto);

    RepairMethod mapToUpdateRepairMethod(UpdateRepairMethodDto methodDto);

    RepairMethodDto mapToNewRepairMethodDto(RepairMethod method);
}