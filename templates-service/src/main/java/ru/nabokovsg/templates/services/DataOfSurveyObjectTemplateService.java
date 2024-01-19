package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.passportData.DataOfSurveyObjectTemplateDto;
import ru.nabokovsg.templates.dto.passportData.NewDataOfSurveyObjectTemplate;
import ru.nabokovsg.templates.dto.passportData.PredicateData;
import ru.nabokovsg.templates.dto.passportData.UpdateDataOfSurveyObjectTemplate;
import ru.nabokovsg.templates.models.DataOfSurveyObjectTemplate;

import java.util.List;

public interface DataOfSurveyObjectTemplateService {

    List<DataOfSurveyObjectTemplateDto> save(Long objectTypeId
                                            , List<NewDataOfSurveyObjectTemplate> characteristicsDto);

   List<DataOfSurveyObjectTemplateDto> update(Long objectTypeId
                                             , List<UpdateDataOfSurveyObjectTemplate> characteristicsDto);

   List<DataOfSurveyObjectTemplateDto> getAll();

   void delete(Long id);

    List<DataOfSurveyObjectTemplate> getAllByPredicate(Long objectTypeId, PredicateData data);
}