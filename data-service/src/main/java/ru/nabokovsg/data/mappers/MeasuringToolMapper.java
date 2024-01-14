package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.data.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.data.dto.measuringTool.UpdateMeasuringToolDto;
import ru.nabokovsg.data.models.MeasuringTool;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeasuringToolMapper {

    MeasuringTool mapToNewMeasuringTool(NewMeasuringToolDto newMeasuringToolDto);

    MeasuringToolDto mapToMeasuringToolDto(MeasuringTool measuringTool);

    MeasuringTool mapToUpdateMeasuringTool(UpdateMeasuringToolDto measuringToolDto);

    ObjectsIds mapFromNewMeasuringToolDto(NewMeasuringToolDto measuringToolDto);

    ObjectsIds mapFromUpdateMeasuringToolDto(UpdateMeasuringToolDto measuringToolDto);
}