package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataParam;

@Mapper(componentModel = "spring")
public interface SubsectionDataProcessingMapper {

    DivisionDataParam mapToDivisionDataParam(DivisionDataDto divisionDataDto);
}
