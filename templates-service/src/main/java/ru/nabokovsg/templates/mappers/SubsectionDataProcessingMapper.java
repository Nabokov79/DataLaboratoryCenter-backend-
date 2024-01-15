package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataParam;

@Mapper(componentModel = "spring")
public interface SubsectionDataProcessingMapper {

    @Mapping(target = "contact", ignore = true)
    DivisionDataParam mapToDivisionDataParam(DivisionDataDto divisionDataDto);
}
