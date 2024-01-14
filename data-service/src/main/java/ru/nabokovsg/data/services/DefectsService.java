package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.defect.DefectDto;
import ru.nabokovsg.data.dto.defect.NewDefectDto;
import ru.nabokovsg.data.dto.defect.UpdateDefectDto;
import ru.nabokovsg.data.dto.objectsType.ObjectsTypeDefectDto;

import java.util.List;

public interface DefectsService {

    List<ObjectsTypeDefectDto> save(List<Long> objectsTypeId, List<NewDefectDto> defects);

    List<DefectDto> update(List<UpdateDefectDto> defects);
}