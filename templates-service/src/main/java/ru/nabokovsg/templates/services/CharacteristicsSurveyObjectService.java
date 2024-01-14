package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.characteristics.CharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.NewCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.UpdateCharacteristicsSurveyObjectDto;

import java.util.List;

public interface CharacteristicsSurveyObjectService {

    List<CharacteristicsSurveyObjectDto> save(String string, Long id, List<NewCharacteristicsSurveyObjectDto> characteristicsDto);

   List<CharacteristicsSurveyObjectDto> update(List<UpdateCharacteristicsSurveyObjectDto> characteristicsDto);

   List<CharacteristicsSurveyObjectDto> getAll();

   void delete(Long id);
}