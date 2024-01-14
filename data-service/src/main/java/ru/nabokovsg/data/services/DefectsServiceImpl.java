package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.defect.DefectDto;
import ru.nabokovsg.data.dto.defect.NewDefectDto;
import ru.nabokovsg.data.dto.defect.UpdateDefectDto;
import ru.nabokovsg.data.dto.objectsType.ObjectsTypeDefectDto;
import ru.nabokovsg.data.dto.sizeParameters.NewSizeParametersDto;
import ru.nabokovsg.data.dto.sizeParameters.UpdateSizeParametersDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.DefectMapper;
import ru.nabokovsg.data.models.Defect;
import ru.nabokovsg.data.models.SizeParameters;
import ru.nabokovsg.data.repository.DefectRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefectsServiceImpl implements DefectsService {

    private final DefectRepository repository;
    private final DefectMapper mapper;
    private final DefectParameterService parameterService;
    private final ObjectsTypeService objectsTypeService;

    @Override
    public List<ObjectsTypeDefectDto> save(List<Long> objectsTypeId, List<NewDefectDto> defectsDto) {
        Map<String, Defect> defectsDb = new ArrayList<>(repository.findDefectParameterByParametersName(
                                                            defectsDto.stream()
                                                                    .map(NewDefectDto::getDefectName)
                                                                    .distinct()
                                                                    .toList()))
                                                            .stream()
                                                            .collect(Collectors.toMap(Defect::getDefectName, d -> d));
        List<SizeParameters> parameters = parameterService.save(defectsDto.stream()
                .map(NewDefectDto::getParameters)
                .flatMap(Collection::stream)
                .toList());
        if (defectsDto.size() != defectsDb.size()) {
            List<Defect> defects = repository.saveAll(
                    defectsDto.stream().filter(d -> !defectsDb.containsKey(d.getDefectName()))
                            .map(d -> {
                                Defect defect = mapper.mapToNewDefect(d);
                                List<String> parametersNames = d.getParameters().stream()
                                        .map(NewSizeParametersDto::getParametersName)
                                        .toList();
                                defect.setParameters(parameters.stream()
                                        .filter(p -> parametersNames.contains(p.getParametersName()))
                                        .toList());
                                return defect;
                            })
                            .toList());
            if (!defectsDb.isEmpty()) {
                defects.addAll(defectsDb.values());
            }
            return objectsTypeService.addDefects(objectsTypeId, defects);
        } else {
            return objectsTypeService.addDefects(objectsTypeId, new ArrayList<>(defectsDb.values()));
        }
    }

    @Override
    public List<DefectDto> update(List<UpdateDefectDto> defectsDto) {
        validateIds(defectsDto.stream().map(UpdateDefectDto::getId).toList());
        List<SizeParameters> parameters = parameterService.update(defectsDto.stream()
                                                                            .map(UpdateDefectDto::getParameters)
                                                                            .flatMap(Collection::stream)
                                                                            .toList());
        List<Defect> defects = defectsDto.stream().map(d -> {
                                                   Defect defect = mapper.mapToUpdateDefect(d);
                                                   List<String> parametersNames = d.getParameters().stream()
                                                           .map(UpdateSizeParametersDto::getParametersName)
                                                           .toList();
                                                   defect.setParameters(parameters.stream()
                                                           .filter(p -> parametersNames.contains(p.getParametersName()))
                                                           .toList());
                                                   return defect;
                                              }).toList();
        return repository.saveAll(defects)
                         .stream()
                         .map(mapper::mapToDefectDto)
                         .toList();
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Defect> defects = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Defect::getId, d -> d));
        if (defects.size() != ids.size() || defects.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(defects.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("defects with ids= %s not found", ids));
        }
    }
}