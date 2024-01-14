package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.building.BuildingDto;
import ru.nabokovsg.data.dto.building.NewBuildingDto;
import ru.nabokovsg.data.dto.building.UpdateBuildingDto;

import java.util.List;

public interface BuildingService {

    List<BuildingDto> save(List<NewBuildingDto> buildingsDto);

    List<BuildingDto> update(List<UpdateBuildingDto> buildingsDto);

    List<BuildingDto> getAll(Long departmentId);

    void  delete(Long id);
}