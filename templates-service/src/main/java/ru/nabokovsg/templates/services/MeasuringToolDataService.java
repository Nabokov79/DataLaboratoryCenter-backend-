package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsectionDada.MeasuringToolDataDto;
import ru.nabokovsg.templates.models.MeasuringToolTemplate;

import java.util.List;

public interface MeasuringToolDataService {

    List<MeasuringToolTemplate> save(List<MeasuringToolDataDto> measuringToolDataDto);
}