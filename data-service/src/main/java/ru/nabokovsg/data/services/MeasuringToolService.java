package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.data.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.data.dto.measuringTool.RequestParameters;
import ru.nabokovsg.data.dto.measuringTool.UpdateMeasuringToolDto;

import java.util.List;

public interface MeasuringToolService {

    List<MeasuringToolDto> save(List<NewMeasuringToolDto> measuringTools);

    List<MeasuringToolDto> update(List<UpdateMeasuringToolDto> measuringTools);

    List<MeasuringToolDto> getAll(RequestParameters parameters);

    void delete(Long id);
}