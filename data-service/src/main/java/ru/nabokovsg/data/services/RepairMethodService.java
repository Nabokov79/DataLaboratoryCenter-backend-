package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.objectsType.ObjectsTypeRepairMethodDto;
import ru.nabokovsg.data.dto.repairMethod.NewRepairMethodDto;
import ru.nabokovsg.data.dto.repairMethod.RepairMethodDto;
import ru.nabokovsg.data.dto.repairMethod.UpdateRepairMethodDto;

import java.util.List;


public interface RepairMethodService {

    List<ObjectsTypeRepairMethodDto> save(List<Long> objectsTypeId, List<NewRepairMethodDto> methodsDto);

    List<RepairMethodDto> update(List<UpdateRepairMethodDto> methodsDto);
}