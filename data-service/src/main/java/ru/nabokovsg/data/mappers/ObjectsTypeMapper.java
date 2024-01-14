package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.objectsType.*;
import ru.nabokovsg.data.models.ObjectsType;

@Mapper(componentModel = "spring")
public interface ObjectsTypeMapper {

    ObjectsType mapToNewObjectType(NewObjectsTypeDto objectsTypesDto);

    ObjectsType mapToUpdateObjectType(UpdateObjectsTypeDto objectsTypesDto);

    ObjectsTypeDto mapToObjectTypeDto(ObjectsType objectsTypes);

    ShortObjectsTypeDto mapToShortObjectsTypeDto(ObjectsType objectsType);

    ObjectsTypeDocumentationDto mapToObjectsTypeDocumentationDto(ObjectsType objectsType);

    ObjectsTypeElementsDto mapToObjectsTypeElementsDto(ObjectsType objectsType);

    ObjectsTypeDefectDto mapToObjectsTypeDefectDto(ObjectsType objectsType);

    ObjectsTypeRepairMethodDto mapToObjectsTypeRepairMethodDto(ObjectsType objectsType);

    NewObjectsTypeDto mapToNewObjectTypeDto(ObjectsType objectsType);

    ObjectsTypeElementsDto mapToObjectTypeElementsDto(ObjectsType objectType);
}