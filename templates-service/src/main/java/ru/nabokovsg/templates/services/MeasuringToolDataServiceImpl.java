package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.subsectionDada.MeasuringToolDataDto;
import ru.nabokovsg.templates.mappers.MeasuringToolDataMapper;
import ru.nabokovsg.templates.models.MeasuringToolTemplate;
import ru.nabokovsg.templates.repository.MeasuringToolDataRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasuringToolDataServiceImpl implements MeasuringToolDataService {

    private final MeasuringToolDataRepository repository;
    private final MeasuringToolDataMapper mapper;

    @Override
    public List<MeasuringToolTemplate> save(List<MeasuringToolDataDto> measuringToolDataDto) {
        Map<String, MeasuringToolTemplate> measuringTools = repository.findAllByValue(measuringToolDataDto
                                                    .stream()
                                                    .map(MeasuringToolDataDto::getValue)
                                                    .toList())
                                                    .stream()
                                                    .collect(Collectors.toMap(MeasuringToolTemplate::getValue, s -> s));
        measuringToolDataDto = measuringToolDataDto.stream()
                                                   .filter(v -> !measuringTools.containsKey(v.getValue()))
                                                   .toList();

        if (!measuringToolDataDto.isEmpty()) {
           repository.saveAll(measuringToolDataDto.stream()
                                                  .map(mapper::mapToMeasuringToolTemplate)
                                                  .toList())
                     .forEach(k -> measuringTools.put(k.getValue(), k));
        }
        return measuringTools.values().stream().toList();
    }
}