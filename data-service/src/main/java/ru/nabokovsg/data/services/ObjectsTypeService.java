package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.objectsType.*;
import ru.nabokovsg.data.models.*;

import java.util.List;

public interface ObjectsTypeService {

    List<ShortObjectsTypeDto> save(List<NewObjectsTypeDto> objectsTypeDto);

    List<ShortObjectsTypeDto> update(List<UpdateObjectsTypeDto> objectsTypeDto);

    ObjectsTypeDto get(Long id);

    ObjectsType getById(Long id);

    List<ObjectsTypeDocumentationDto> addDocumentations(List<Long> ids, List<Documentation> documentations);

    List<ObjectsTypeElementsDto> addElements(List<Long> ids, List<Element> elements);

    List<ObjectsTypeDefectDto> addDefects(List<Long> ids, List<Defect> defects);

    List<ObjectsTypeRepairMethodDto> addRepairMethods(List<Long> ids, List<RepairMethod> methods);

    List<ObjectsTypeDto> getAll(List<Long> ids);

    void delete(Long id);
}