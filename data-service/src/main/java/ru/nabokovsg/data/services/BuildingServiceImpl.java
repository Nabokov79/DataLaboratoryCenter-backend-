package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.building.BuildingDto;
import ru.nabokovsg.data.dto.building.NewBuildingDto;
import ru.nabokovsg.data.dto.building.UpdateBuildingDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.BuildingMapper;
import ru.nabokovsg.data.mappers.DepartmentMapper;
import ru.nabokovsg.data.models.Building;
import ru.nabokovsg.data.models.DataBuilder;
import ru.nabokovsg.data.models.enums.BuilderType;
import ru.nabokovsg.data.repository.BuildingRepository;
import ru.nabokovsg.data.services.builder.DataFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository repository;
    private final BuildingMapper mapper;
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;
    private final DataFactory factory;

    @Override
    public List<BuildingDto> save(List<NewBuildingDto> buildingsDto) {
        List<ObjectsIds> ids = buildingsDto.stream()
                                            .map(mapper::mapFromNewBuildingDto)
                                            .toList();
        Map<Long, Building> buildings = repository.findAllByAddressId(
                                                                     ids.stream()
                                                                        .map(ObjectsIds::getAddressId)
                                                                        .toList())
                                                        .stream()
                                                        .collect(Collectors.toMap(b -> b.getAddress().getId(), b -> b));
        if (!buildings.isEmpty()) {
            buildingsDto = buildingsDto.stream()
                                       .filter(d -> !buildings.containsKey(d.getAddressId()))
                                       .toList();
        }
        if (!buildingsDto.isEmpty()) {
            DataBuilder builder = factory.getBuilder(ids, BuilderType.BUILDING);
            repository.saveAll(
                    buildingsDto.stream()
                            .map(b -> mapper.mapToNewBuilding(b
                                    , builder.getAddresses().get(b.getAddressId())
                                    , builder.getDepartments().get(b.getDepartmentId()))
                           )
                            .toList()).forEach(d -> buildings.put(d.getAddress().getId(), d));
        }

        return getListDto(buildings.values().stream().toList());
    }

    @Override
    public List<BuildingDto> update(List<UpdateBuildingDto> buildingsDto) {
        validateIds(buildingsDto.stream().map(UpdateBuildingDto::getId).toList());
        DataBuilder builder = factory.getBuilder(buildingsDto.stream()
                                                             .map(mapper::mapFromUpdateBuildingDto)
                                                             .toList()
                                               , BuilderType.BUILDING);
        return getListDto(repository.saveAll(buildingsDto
                                                        .stream()
                                                        .map(b -> mapper.mapToUpdateBuilding(b
                                                                , builder.getAddresses().get(b.getAddressId())
                                                                , builder.getDepartments().get(b.getDepartmentId()))
                                                        )
                                                        .toList()));
    }

    @Override
    public List<BuildingDto> getAll(Long departmentId) {
        return repository.findAllByDepartment(departmentMapper.mapToDepartment(departmentService.get(departmentId)))
                         .stream()
                         .map(mapper::mapToBuildingDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Building with id=%s not found for delete.", id));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Building> buildings = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Building::getId, m -> m));
        if (buildings.size() != ids.size() || buildings.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(buildings.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("Buildings with ids= %s not found", ids));
        }
    }

    private List<BuildingDto> getListDto(List<Building> buildings) {
        return buildings.stream().map(mapper::mapToBuildingDto).toList();
    }
}