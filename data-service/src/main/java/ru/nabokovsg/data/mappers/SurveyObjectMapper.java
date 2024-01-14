package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.surveyObject.NewSurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.ShortSurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.SurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.UpdateSurveyObjectDto;
import ru.nabokovsg.data.models.SurveyObject;

@Mapper(componentModel = "spring")
public interface SurveyObjectMapper {

    SurveyObject mapToNewObjectSurvey(NewSurveyObjectDto objectDto);

    SurveyObject mapToUpdateObjectSurvey(UpdateSurveyObjectDto objectDto);

    ShortSurveyObjectDto mapToShortSurveyObjectDto(SurveyObject object);

    SurveyObjectDto mapToObjectSurveyDto(SurveyObject object);

    ObjectsIds mapFromNewObjectSurveyIds(NewSurveyObjectDto objectDto);

    ObjectsIds mapFromUpdateObjectSurveyIds(UpdateSurveyObjectDto objectDto);
}