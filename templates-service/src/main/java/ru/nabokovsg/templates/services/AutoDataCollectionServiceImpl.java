package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.autoData.AutoDataCollectionDto;
import ru.nabokovsg.templates.mappers.AutoDataCollectionMapper;
import ru.nabokovsg.templates.models.AutoDataCollection;
import ru.nabokovsg.templates.repository.AutoDataCollectionRepository;

@Service
@RequiredArgsConstructor
public class AutoDataCollectionServiceImpl implements AutoDataCollectionService {

    private final AutoDataCollectionRepository repository;

    private final AutoDataCollectionMapper mapper;

    @Override
    public AutoDataCollection save(AutoDataCollectionDto autoDataCollectionDto) {
        return repository.save(mapper.mapToNewAutoDataCollectionMapper(autoDataCollectionDto));
    }

    @Override
    public AutoDataCollection update(AutoDataCollection autoDataCollection
                                   , AutoDataCollectionDto autoDataCollectionDto) {
        return repository.save(mapper.mapToUpdateAutoDataCollectionMapper(autoDataCollection, autoDataCollectionDto));
    }
}