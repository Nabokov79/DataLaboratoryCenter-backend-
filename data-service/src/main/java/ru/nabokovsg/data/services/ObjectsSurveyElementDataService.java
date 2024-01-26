package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.rejection.surveyObjectElement.NewSurveyObjectElementDataDto;
import ru.nabokovsg.data.dto.rejection.surveyObjectElement.SurveyObjectElementDataDto;
import ru.nabokovsg.data.dto.rejection.surveyObjectElement.UpdateSurveyObjectElementDataDto;
import ru.nabokovsg.data.models.ElementRejectionParameters;

import java.util.List;

public interface ObjectsSurveyElementDataService {

    List<SurveyObjectElementDataDto> save(Long surveyObjectId, List<NewSurveyObjectElementDataDto> elementsDataDto);

    List<SurveyObjectElementDataDto> update(Long surveyObjectId, List<UpdateSurveyObjectElementDataDto> elementsDataDto);

    void delete(Long id);

    void addElementRejectionParameters(Long elementId, ElementRejectionParameters rejectionParameters);
}