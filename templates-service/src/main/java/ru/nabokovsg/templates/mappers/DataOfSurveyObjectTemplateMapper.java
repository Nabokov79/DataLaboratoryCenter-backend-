package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.passportData.*;
import ru.nabokovsg.templates.models.DataOfSurveyObjectTemplate;

@Mapper(componentModel = "spring")
public interface DataOfSurveyObjectTemplateMapper {

    DataOfSurveyObjectTemplate mapToNewDataOfSurveyObjectTemplate(NewPassportDataTemplateDto passportData, PredicateData predicate);

    DataOfSurveyObjectTemplate mapToUpdateDataOfSurveyObjectTemplate(UpdatePassportDataTemplateDto passportData, PredicateData predicate);

    PredicateData mapToPredicateDataForNewData(NewDataOfSurveyObjectTemplateDto dataOfSurveyObjectDto);

    PredicateData mapToPredicateDataForUpdateData(UpdateDataOfSurveyObjectTemplateDto characteristicDto);

    DataOfSurveyObjectTemplateDto mapToDataOfSurveyObjectTemplateDto(DataOfSurveyObjectTemplate characteristic);
}