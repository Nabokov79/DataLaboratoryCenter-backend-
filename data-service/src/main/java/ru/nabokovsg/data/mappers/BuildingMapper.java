package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.building.BuildingDto;
import ru.nabokovsg.data.dto.building.NewBuildingDto;
import ru.nabokovsg.data.dto.building.UpdateBuildingDto;
import ru.nabokovsg.data.models.Address;
import ru.nabokovsg.data.models.Building;
import ru.nabokovsg.data.models.Department;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

    @Mapping(source = "address", target = "address")
    @Mapping(source = "department", target = "department")
    @Mapping(target = "id", ignore = true)
    Building mapToNewBuilding(NewBuildingDto buildingDto, Address address, Department department);

    @Mapping(source = "address", target = "address")
    @Mapping(source = "department", target = "department")
    @Mapping(source = "buildingDto.id", target = "id")
    Building mapToUpdateBuilding(UpdateBuildingDto buildingDto, Address address, Department department);

    BuildingDto mapToBuildingDto(Building building);

    ObjectsIds mapFromNewBuildingDto(NewBuildingDto buildingDto);

    ObjectsIds mapFromUpdateBuildingDto(UpdateBuildingDto buildingDto);
}