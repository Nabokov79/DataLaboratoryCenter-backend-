package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.characteristics.CharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.NewCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.UpdateCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.models.CharacteristicsSurveyObject;
import ru.nabokovsg.templates.models.enums.DataType;

import java.util.List;

public interface CharacteristicsSurveyObjectService {

    List<CharacteristicsSurveyObjectDto> save(Long objectTypeId
                                            , List<NewCharacteristicsSurveyObjectDto> characteristicsDto);

   List<CharacteristicsSurveyObjectDto> update(Long objectTypeId
                                             , List<UpdateCharacteristicsSurveyObjectDto> characteristicsDto);

   List<CharacteristicsSurveyObjectDto> getAll();

   void delete(Long id);

    List<CharacteristicsSurveyObject> getAllByPredicate(Long objectTypeId, DataType type);

    List<CharacteristicsSurveyObject> getAllById(List<Long> ids);
}