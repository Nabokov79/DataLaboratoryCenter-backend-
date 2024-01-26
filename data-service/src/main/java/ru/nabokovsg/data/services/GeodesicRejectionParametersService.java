package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.rejection.surveyObject.GeodesicRejectionParametersDto;
import ru.nabokovsg.data.dto.rejection.surveyObject.NewGeodesicRejectionParametersDto;
import ru.nabokovsg.data.dto.rejection.surveyObject.UpdateGeodesicRejectionParametersDto;

import java.util.List;

public interface GeodesicRejectionParametersService {

    GeodesicRejectionParametersDto save(NewGeodesicRejectionParametersDto rejectionParametersDto);

    List<GeodesicRejectionParametersDto> addExisting(Long firstSurveyObjectId, Long secondSurveyObjectId);

    GeodesicRejectionParametersDto update(UpdateGeodesicRejectionParametersDto rejectionParametersDto);

    GeodesicRejectionParametersDto get(Long id);

    void delete(Long id);
}
