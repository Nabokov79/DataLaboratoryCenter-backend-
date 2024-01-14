package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.objectsSurveyElementData.NewObjectsSurveyElementDataDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.SurveyObjectElementDataDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.UpdateObjectsSurveyElementDataDto;
import ru.nabokovsg.data.models.SurveyObjectElementData;

@Mapper(componentModel = "spring")
public interface ObjectsSurveyElementDataMapper {

    SurveyObjectElementData mapFromNewObjectsSurveyElement(NewObjectsSurveyElementDataDto elementDto);

    SurveyObjectElementData mapFromUpdateObjectsSurveyElement(UpdateObjectsSurveyElementDataDto elementDto);

    SurveyObjectElementDataDto mapFromObjectsSurveyElementDto(SurveyObjectElementData element);
}