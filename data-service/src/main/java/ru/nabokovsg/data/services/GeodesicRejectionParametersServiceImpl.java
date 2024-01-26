package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.rejection.surveyObject.GeodesicRejectionParametersDto;
import ru.nabokovsg.data.dto.rejection.surveyObject.NewGeodesicRejectionParametersDto;
import ru.nabokovsg.data.dto.rejection.surveyObject.UpdateGeodesicRejectionParametersDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.GeodesicRejectionParametersMapper;
import ru.nabokovsg.data.models.GeodesicRejectionParameters;
import ru.nabokovsg.data.repository.GeodesicRejectionParametersRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeodesicRejectionParametersServiceImpl implements GeodesicRejectionParametersService {

    private final GeodesicRejectionParametersRepository repository;
    private final GeodesicRejectionParametersMapper mapper;
    private final SurveyObjectService surveyObjectService;

    @Override
    public GeodesicRejectionParametersDto save(NewGeodesicRejectionParametersDto rejectionParametersDto) {
        Map<Boolean, GeodesicRejectionParameters> rejectionParametersDb = repository.findAllBySurveyObjectId(
                         rejectionParametersDto.getSurveyObjectId())
                                               .stream()
                                               .collect(Collectors.toMap(GeodesicRejectionParameters::getFull, g -> g));
        GeodesicRejectionParameters rejectionParameters = rejectionParametersDb.get(rejectionParametersDto.getFull());
        if (rejectionParameters == null) {
            rejectionParameters = repository.save(mapper.mapToNewGeodesicRejectionParameters(rejectionParametersDto));
            rejectionParametersDb.put(rejectionParameters.getFull(), rejectionParameters);
            surveyObjectService.addGeodesicRejectionParameters(rejectionParametersDto.getSurveyObjectId()
                                                             , new HashSet<>(rejectionParametersDb.values()));
        }
        return mapper.mapToGeodesicRejectionParametersDto(rejectionParameters);
    }

    @Override
    public List<GeodesicRejectionParametersDto> addExisting(Long firstSurveyObjectId, Long secondSurveyObjectId) {
        Set<GeodesicRejectionParameters> rejectionParameters = repository.findAllBySurveyObjectId(firstSurveyObjectId);
        if (rejectionParameters.isEmpty()) {
            throw new NotFoundException(
                    String.format("GeodesicRejectionParameters for survey object with id=%s not found",
                                                                                                  firstSurveyObjectId));
        }
        surveyObjectService.addGeodesicRejectionParameters(secondSurveyObjectId, rejectionParameters);
        return rejectionParameters.stream().map(mapper::mapToGeodesicRejectionParametersDto).toList();
    }

    @Override
    public GeodesicRejectionParametersDto update(UpdateGeodesicRejectionParametersDto rejectionParametersDto) {
        if (repository.existsById(rejectionParametersDto.getId())) {
            return mapper.mapToGeodesicRejectionParametersDto(
                    repository.save(mapper.mapToUpdateGeodesicRejectionParameters(rejectionParametersDto)));
        }
        throw new NotFoundException(String.format(
                "GeodesicRejectionParameters with id=%s not found for update", rejectionParametersDto.getId()));
    }

    @Override
    public GeodesicRejectionParametersDto get(Long id) {
        return mapper.mapToGeodesicRejectionParametersDto(
                repository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                String.format("GeodesicRejectionParameters with id=%s not found", id))));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("GeodesicRejectionParameters with id=%s not found for delete", id));
    }
}