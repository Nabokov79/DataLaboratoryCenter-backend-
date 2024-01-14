package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.columns.NewColumnHeaderTemplateDto;
import ru.nabokovsg.templates.dto.columns.UpdateColumnHeaderTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.ColumnHeaderTemplateMapper;
import ru.nabokovsg.templates.models.ColumnHeaderTemplate;
import ru.nabokovsg.templates.repository.ColumnHeaderTemplateRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ColumnHeaderTemplateServiceImpl implements ColumnHeaderTemplateService {

    private final ColumnHeaderTemplateRepository repository;
    private final ColumnHeaderTemplateMapper mapper;

    @Override
    public List<ColumnHeaderTemplate> save(List<NewColumnHeaderTemplateDto> columnHeaderTemplateDto) {
        Map<String, ColumnHeaderTemplate> columnHeadersDb = repository.findAllByCellName(columnHeaderTemplateDto
                        .stream()
                        .map(NewColumnHeaderTemplateDto::getHeading)
                        .toList())
                .stream()
                .collect(Collectors.toMap(ColumnHeaderTemplate::getHeading, c -> c));
        columnHeaderTemplateDto = columnHeaderTemplateDto.stream()
                .filter(s -> !columnHeadersDb.containsKey(s.getHeading()))
                .toList();
        if (columnHeaderTemplateDto.isEmpty()) {
            return columnHeadersDb.values().stream().toList();
        }
        return Stream.of(columnHeadersDb.values()
                        , repository.saveAll(columnHeaderTemplateDto
                                .stream()
                                .map(mapper::mapToNewColumnHeaderTemplates)
                                .toList()))
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public List<ColumnHeaderTemplate> update(List<UpdateColumnHeaderTemplateDto> columnHeaderTemplateDto) {
        validateIds(columnHeaderTemplateDto.stream().map(UpdateColumnHeaderTemplateDto::getId).toList());
        return repository.saveAll(columnHeaderTemplateDto.stream()
                                                         .map(mapper::mapToUpdateColumnHeaderTemplates)
                                                         .toList()
        );
    }

    private void validateIds(List<Long> ids) {
        Map<Long, ColumnHeaderTemplate> columnHeaderTemplate = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(ColumnHeaderTemplate::getId, m -> m));
        if (columnHeaderTemplate.size() != ids.size() || columnHeaderTemplate.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(columnHeaderTemplate.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("Column header template with ids= %s not found", ids));

        }
    }
}