package ru.nabokovsg.templates.services;

import jakarta.validation.Valid;
import ru.nabokovsg.templates.dto.autoData.AutoDataCollectionDto;
import ru.nabokovsg.templates.models.AutoDataCollection;

public interface AutoDataCollectionService {

    AutoDataCollection save(@Valid AutoDataCollectionDto autoDataCollectionDto);

    AutoDataCollection update(AutoDataCollection autoDataCollection
                            , @Valid AutoDataCollectionDto autoDataCollectionDto);
}