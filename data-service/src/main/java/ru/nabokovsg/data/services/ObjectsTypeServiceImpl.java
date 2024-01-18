package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.objectsType.*;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.ObjectsTypeMapper;
import ru.nabokovsg.data.models.*;
import ru.nabokovsg.data.repository.ObjectsTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectsTypeServiceImpl implements ObjectsTypeService {

    private final ObjectsTypeRepository repository;
    private final ObjectsTypeMapper mapper;

    @Override
    public List<ShortObjectsTypeDto> save(List<NewObjectsTypeDto> objectsTypeDto) {
        List<ObjectsType> objectsTypesDb = repository.findAll();
        List<NewObjectsTypeDto> objectTypes = objectsTypesDb.stream()
                                                            .map(mapper::mapToNewObjectTypeDto)
                                                            .toList();
        List<ObjectsType> objectsTypes = objectsTypeDto.stream()
                                                       .filter(o -> !objectTypes.contains(o))
                                                       .distinct()
                                                       .map(mapper::mapToNewObjectType)
                                                       .toList();
        if (objectsTypes.isEmpty()) {
            return getShortObjectsTypeListDto(objectsTypesDb);
        }
        if (objectsTypes.size() != objectsTypeDto.size()) {
            objectsTypesDb.addAll(repository.saveAll(objectsTypes));
            return getShortObjectsTypeListDto(objectsTypesDb);
        } else {
            return getShortObjectsTypeListDto(repository.saveAll(objectsTypes));
        }
    }

    @Override
    public List<ShortObjectsTypeDto> update(List<UpdateObjectsTypeDto> objectsTypeDto) {
        validateByIds(objectsTypeDto.stream().map(UpdateObjectsTypeDto::getId).toList());
        return getShortObjectsTypeListDto(repository.saveAll(objectsTypeDto.stream()
                                                                            .map(mapper::mapToUpdateObjectType)
                                                                            .toList())
        );
    }

    @Override
    public ObjectsTypeDto get(Long id) {
        return mapper.mapToObjectTypeDto(getById(id));
    }

    @Override
    public ObjectsType getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Objects type with id=%s not found", id)));
    }

    @Override
    public List<ObjectsTypeDocumentationDto> addDocumentations(List<Long> ids, List<Documentation> documentations) {
        return repository.saveAll(getAllByIds(ids).stream()
                                                  .peek(o -> o.setDocumentations(documentations))
                                                  .toList())
                                            .stream()
                                            .map(mapper::mapToObjectsTypeDocumentationDto)
                                            .toList();
    }

    @Override
    public List<ObjectsTypeElementsDto> addElements(List<Long> ids, List<Element> elements) {
        return repository.saveAll(getAllByIds(ids)
                         .stream()
                         .peek(o -> o.getElements().addAll(elements.stream()
                                                                   .filter(e -> e.getObjectsTypeId().equals(o.getId()))
                                                                   .toList())
                         )
                        .toList())
                        .stream()
                        .map(mapper::mapToObjectsTypeElementsDto)
                        .toList();
    }

    @Override
    public List<ObjectsTypeDefectDto> addDefects(List<Long> ids, List<Defect> defects) {
        return repository.saveAll(getAllByIds(ids).stream()
                                                  .peek(o -> o.setDefects(defects))
                                                  .toList())
                                            .stream()
                                            .map(mapper::mapToObjectsTypeDefectDto)
                                            .collect(Collectors.toList());
    }

    @Override
    public List<ObjectsTypeRepairMethodDto> addRepairMethods(List<Long> ids, List<RepairMethod> methods) {
        return repository.saveAll(getAllByIds(ids).stream()
                                                  .peek(o -> o.setRepairMethods(methods))
                                                  .toList())
                                            .stream()
                                            .map(mapper::mapToObjectsTypeRepairMethodDto)
                                            .toList();
    }

    private List<ObjectsType> getAllByIds(List<Long> ids) {
        List<ObjectsType> objectsTypes;
        if(ids.isEmpty()) {
            objectsTypes = repository.findAll();
        } else {
            objectsTypes = repository.findAllById(ids);
        }
        if (objectsTypes.isEmpty()) {
            throw new NotFoundException("Objects type not found");
        }
        return objectsTypes;
    }


    @Override
    public List<ShortObjectsTypeDto> getAll(List<Long> ids) {
        List<ObjectsType> objectsTypes;
        if (ids == null) {
            objectsTypes = repository.findAll();
        } else {
            objectsTypes = repository.findAllById(ids);
        }
        return objectsTypes.stream().map(mapper::mapToShortObjectsTypeDto).toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("measuring tool with id = %s not found for delete", id));
    }

    private void validateByIds(List<Long> ids) {
        Map<Long, ObjectsType> objectsTypes = getAllByIds((ids))
                .stream().collect(Collectors.toMap(ObjectsType::getId, o -> o));
        if (objectsTypes.size() != ids.size() || objectsTypes.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(objectsTypes.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("objects types with ids= %s not found", ids));
        }
    }

    private List<ShortObjectsTypeDto> getShortObjectsTypeListDto(List<ObjectsType> objectsTypes) {
        return objectsTypes.stream()
                            .map(mapper::mapToShortObjectsTypeDto)
                            .toList();
    }
}