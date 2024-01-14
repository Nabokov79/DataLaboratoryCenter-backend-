package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.surveyObject.NewSurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.ShortSurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.SurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.UpdateSurveyObjectDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.SurveyObjectMapper;
import ru.nabokovsg.data.models.Building;
import ru.nabokovsg.data.models.DataBuilder;
import ru.nabokovsg.data.models.ObjectsType;
import ru.nabokovsg.data.models.SurveyObject;
import ru.nabokovsg.data.models.enums.BuilderType;
import ru.nabokovsg.data.repository.SurveyObjectRepository;
import ru.nabokovsg.data.services.builder.DataFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyObjectServiceImpl implements SurveyObjectService {

    private final SurveyObjectRepository repository;
    private final SurveyObjectMapper mapper;
    private final DataFactory factory;

    @Override
    public List<ShortSurveyObjectDto> save(List<NewSurveyObjectDto> objectsDto) {
        DataBuilder builder = getData(objectsDto.stream()
                                                .map(mapper::mapFromNewObjectSurveyIds)
                                                .toList());
        Map<Long, Building> buildings = convertBuildings(builder);
        Map<Long, ObjectsType> types = convertObjectsType(builder);
        return getListDto(repository.saveAll(objectsDto.stream()
                                                    .map(o -> {
                                                                SurveyObject object = mapper.mapToNewObjectSurvey(o);
                                                                object.setBuilding(buildings.get(o.getBuildingId()));
                                                                object.setObjectType(types.get(o.getObjectTypeId()));
                                                                return object;
                                                            })
                                                    .toList()));
    }

    @Override
    public List<ShortSurveyObjectDto> update(List<UpdateSurveyObjectDto> objectsDto) {
        validateIds(objectsDto.stream().map(UpdateSurveyObjectDto::getId).toList());
        DataBuilder builder = getData(objectsDto.stream()
                                                .map(mapper::mapFromUpdateObjectSurveyIds)
                                                .toList());
        Map<Long, Building> buildings = convertBuildings(builder);
        Map<Long, ObjectsType> types = convertObjectsType(builder);
        return getListDto(repository.saveAll(objectsDto.stream()
                                                        .map(o -> {
                                                            SurveyObject object = mapper.mapToUpdateObjectSurvey(o);
                                                            object.setBuilding(buildings.get(o.getBuildingId()));
                                                            object.setObjectType(types.get(o.getObjectTypeId()));
                                                            return object;
                                                        })
                                                        .toList()));
    }

    @Override
    public SurveyObjectDto get(Long id) {
        return mapper.mapToObjectSurveyDto(
                repository.findById(id)
                        .orElseThrow(
                                () -> new NotFoundException(String.format("ObjectSurvey with id=%s not found", id)))
        );
    }

    @Override
    public List<SurveyObjectDto> getAll(Long buildingId) {
        return repository.findAllByBuildingId(buildingId).stream().map(mapper::mapToObjectSurveyDto).toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("objects with id= %s not found for delete", id));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, SurveyObject> objects = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SurveyObject::getId, n -> n));
        if (objects.size() != ids.size() || objects.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(objects.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("ObjectSurvey with ids= %s not found", ids));
        }
    }

    private DataBuilder getData(List<ObjectsIds> ids) {
        return factory.getBuilder(ids, BuilderType.OBJECT);
    }

    private Map<Long, Building> convertBuildings(DataBuilder builder) {
        return builder.getBuildings().stream().collect(Collectors.toMap(Building::getId, b -> b));
    }

    private Map<Long, ObjectsType> convertObjectsType(DataBuilder builder) {
        return builder.getObjectsTypes().stream().collect(Collectors.toMap(ObjectsType::getId, t -> t));
    }

    private List<ShortSurveyObjectDto> getListDto(List<SurveyObject> surveyObjects) {
        return surveyObjects.stream()
                            .map(mapper::mapToShortSurveyObjectDto)
                            .toList();
    }
}