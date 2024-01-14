package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.templates.dto.autoData.AutoDataCollectionDto;
import ru.nabokovsg.templates.models.AutoDataCollection;

@Mapper(componentModel = "spring")
public interface AutoDataCollectionMapper {

    AutoDataCollection mapToNewAutoDataCollectionMapper(AutoDataCollectionDto autoDataCollectionDto);

    AutoDataCollection mapToUpdateAutoDataCollectionMapper(@MappingTarget AutoDataCollection autoDataCollection
                                                                        , AutoDataCollectionDto autoDataCollectionDto);
}