package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.templates.dto.characteristics.CharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.NewCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.UpdateCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.models.CharacteristicsSurveyObject;

@Mapper(componentModel = "spring")
public interface CharacteristicsSurveyObjectMapper {

    @Mapping(target = "id", ignore = true)
    CharacteristicsSurveyObject mapToNewCharacteristicsSurveyObject(
                                                            NewCharacteristicsSurveyObjectDto characteristicDto);

    CharacteristicsSurveyObject mapToUpdateCharacteristicsSurveyObject(
                                                                UpdateCharacteristicsSurveyObjectDto characteristicDto);

    CharacteristicsSurveyObjectDto mapToCharacteristicsSurveyObjectDto(CharacteristicsSurveyObject characteristic);
}