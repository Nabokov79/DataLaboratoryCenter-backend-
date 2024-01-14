package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.objectsSurveyElementData.NewObjectsSurveyElementDataDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.SurveyObjectElementDataDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.UpdateObjectsSurveyElementDataDto;

import java.util.List;

public interface ObjectsSurveyElementDataService {

    List<SurveyObjectElementDataDto> save(Long surveyObjectId, List<NewObjectsSurveyElementDataDto> elementsDataDto);

    List<SurveyObjectElementDataDto> update(Long surveyObjectId, List<UpdateObjectsSurveyElementDataDto> elementsDataDto);

    void delete(Long id);
}