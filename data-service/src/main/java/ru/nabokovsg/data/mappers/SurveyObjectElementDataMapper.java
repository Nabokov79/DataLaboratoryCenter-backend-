package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.rejection.surveyObjectElement.NewSurveyObjectElementDataDto;
import ru.nabokovsg.data.dto.rejection.surveyObjectElement.SurveyObjectElementDataDto;
import ru.nabokovsg.data.dto.rejection.surveyObjectElement.UpdateSurveyObjectElementDataDto;
import ru.nabokovsg.data.models.SurveyObjectElementData;

@Mapper(componentModel = "spring")
public interface SurveyObjectElementDataMapper {

    SurveyObjectElementData mapFromNewObjectsSurveyElement(NewSurveyObjectElementDataDto elementDto);

    SurveyObjectElementData mapFromUpdateObjectsSurveyElement(UpdateSurveyObjectElementDataDto elementDto);

    SurveyObjectElementDataDto mapFromObjectsSurveyElementDto(SurveyObjectElementData element);
}