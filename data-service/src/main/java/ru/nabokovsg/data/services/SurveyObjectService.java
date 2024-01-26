package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.surveyObject.NewSurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.ShortSurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.SurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.UpdateSurveyObjectDto;
import ru.nabokovsg.data.models.GeodesicRejectionParameters;

import java.util.List;
import java.util.Set;

public interface SurveyObjectService {

    List<ShortSurveyObjectDto> save(List<NewSurveyObjectDto> objectsDto);

    List<ShortSurveyObjectDto> update(List<UpdateSurveyObjectDto> objectsDto);

    SurveyObjectDto get(Long id);

    List<SurveyObjectDto> getAll(Long buildingId);

     void delete(Long id);

     void addGeodesicRejectionParameters(Long surveyObjectId, Set<GeodesicRejectionParameters> rejectionParameters);
}