package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.templates.dto.subsectionDada.MeasuringToolDataDto;
import ru.nabokovsg.templates.models.MeasuringToolTemplate;

@Mapper(componentModel = "spring")
public interface MeasuringToolDataMapper {

    @Mapping(target = "id", ignore = true)
    MeasuringToolTemplate mapToMeasuringToolTemplate(MeasuringToolDataDto measuringToolDataDto);
}