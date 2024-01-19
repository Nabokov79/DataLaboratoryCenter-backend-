package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.templates.dto.passportData.DataOfSurveyObjectTemplateDto;
import ru.nabokovsg.templates.dto.passportData.NewDataOfSurveyObjectTemplate;
import ru.nabokovsg.templates.dto.passportData.UpdateDataOfSurveyObjectTemplate;
import ru.nabokovsg.templates.models.DataOfSurveyObjectTemplate;

@Mapper(componentModel = "spring")
public interface DataOfSurveyObjectTemplateMapper {

    @Mapping(target = "id", ignore = true)
    DataOfSurveyObjectTemplate mapToNewCharacteristicsSurveyObject(
                                                            NewDataOfSurveyObjectTemplate characteristicDto);

    DataOfSurveyObjectTemplate mapToUpdateCharacteristicsSurveyObject(
                                                                UpdateDataOfSurveyObjectTemplate characteristicDto);

    DataOfSurveyObjectTemplateDto mapToCharacteristicsSurveyObjectDto(DataOfSurveyObjectTemplate characteristic);
}