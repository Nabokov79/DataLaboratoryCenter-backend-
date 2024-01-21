package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.passportData.DataOfSurveyObjectTemplateDto;
import ru.nabokovsg.templates.dto.passportData.NewDataOfSurveyObjectTemplateDto;
import ru.nabokovsg.templates.dto.passportData.PredicateData;
import ru.nabokovsg.templates.dto.passportData.UpdateDataOfSurveyObjectTemplateDto;
import ru.nabokovsg.templates.models.DataOfSurveyObjectTemplate;

import java.util.List;

public interface DataOfSurveyObjectTemplateService {

    List<DataOfSurveyObjectTemplateDto> save(NewDataOfSurveyObjectTemplateDto dataOfSurveyObjectDto);

   List<DataOfSurveyObjectTemplateDto> update(UpdateDataOfSurveyObjectTemplateDto dataOfSurveyObjectDto);

   List<DataOfSurveyObjectTemplateDto> getAll();

   void delete(Long id);

    List<DataOfSurveyObjectTemplate> getAllDataOfSurveyObjectTemplate(PredicateData predicate);
}