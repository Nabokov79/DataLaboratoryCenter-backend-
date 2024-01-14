package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.element.ElementDto;
import ru.nabokovsg.data.dto.element.NewElementDto;
import ru.nabokovsg.data.dto.element.UpdateElementDto;
import ru.nabokovsg.data.dto.objectsType.ObjectsTypeElementsDto;

import java.util.List;

public interface ElementService {

    List<ObjectsTypeElementsDto> save(List<Long> objectsTypeId, List<NewElementDto> elementsDto);

    List<ElementDto> update(List<UpdateElementDto> elementsDto);

    ObjectsTypeElementsDto getAll(Long objectsTypeId);
}