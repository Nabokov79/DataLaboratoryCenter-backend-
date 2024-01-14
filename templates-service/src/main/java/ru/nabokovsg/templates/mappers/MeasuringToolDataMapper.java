package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.subsectionDada.MeasuringToolDataDto;
import ru.nabokovsg.templates.models.MeasuringToolTemplate;

@Mapper(componentModel = "spring")
public interface MeasuringToolDataMapper {

    MeasuringToolTemplate mapToMeasuringToolTemplate(MeasuringToolDataDto measuringToolDataDto);
}