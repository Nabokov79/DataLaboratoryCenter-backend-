package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.building.BuildingDto;
import ru.nabokovsg.data.dto.building.NewBuildingDto;
import ru.nabokovsg.data.dto.building.UpdateBuildingDto;
import ru.nabokovsg.data.models.Building;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

    Building mapToNewBuilding(NewBuildingDto buildingDto);

    Building mapToUpdateBuilding(UpdateBuildingDto buildingDto);

    BuildingDto mapToBuildingDto(Building building);

    ObjectsIds mapFromNewBuildingDto(NewBuildingDto buildingDto);

    ObjectsIds mapFromUpdateBuildingDto(UpdateBuildingDto buildingDto);
}