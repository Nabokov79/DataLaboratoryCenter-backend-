package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.section.NewSectionTemplateDto;
import ru.nabokovsg.templates.dto.section.SectionTemplateDto;
import ru.nabokovsg.templates.dto.section.UpdateSectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.ShortSubsectionTemplateDto;
import ru.nabokovsg.templates.models.CharacteristicsSurveyObject;
import ru.nabokovsg.templates.models.ProtocolReportTemplate;
import ru.nabokovsg.templates.models.RecommendationTemplate;
import ru.nabokovsg.templates.models.SubsectionTemplate;

import java.util.List;

public interface SectionTemplateService {

    List<SectionTemplateDto> save(Long reportId, List<NewSectionTemplateDto> sectionsDto);

    List<SectionTemplateDto> update(List<UpdateSectionTemplateDto> sectionsDto);

    SectionTemplateDto get(Long id);

    List<ShortSubsectionTemplateDto> getAllSubsections(Long id);

    void addSubsection(Long id, SubsectionTemplate subsection);

    void addProtocol(Long id, ProtocolReportTemplate protocol);

    void addCharacteristicsSurveyObject(Long id, List<CharacteristicsSurveyObject> characteristics);

    void addRecommendation(Long id, List<RecommendationTemplate> recommendations);
}