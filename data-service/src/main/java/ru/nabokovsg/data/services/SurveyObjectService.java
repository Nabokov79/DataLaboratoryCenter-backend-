package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.surveyObject.NewSurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.ShortSurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.SurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.UpdateSurveyObjectDto;

import java.util.List;

public interface SurveyObjectService {

    List<ShortSurveyObjectDto> save(List<NewSurveyObjectDto> objectsDto);

    List<ShortSurveyObjectDto> update(List<UpdateSurveyObjectDto> objectsDto);

    SurveyObjectDto get(Long id);

    List<SurveyObjectDto> getAll(Long buildingId);

     void delete(Long id);
}