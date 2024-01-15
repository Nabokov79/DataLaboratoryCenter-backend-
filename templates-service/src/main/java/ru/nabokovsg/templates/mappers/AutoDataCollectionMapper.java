package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.templates.dto.autoData.AutoDataCollectionDto;
import ru.nabokovsg.templates.models.AutoDataCollection;

@Mapper(componentModel = "spring")
public interface AutoDataCollectionMapper {

    @Mapping(target = "id", ignore = true)
    AutoDataCollection mapToNewAutoDataCollectionMapper(AutoDataCollectionDto autoDataCollectionDto);

    @Mapping(target = "id", ignore = true)
    AutoDataCollection mapToUpdateAutoDataCollectionMapper(@MappingTarget AutoDataCollection autoDataCollection
                                                                        , AutoDataCollectionDto autoDataCollectionDto);
}