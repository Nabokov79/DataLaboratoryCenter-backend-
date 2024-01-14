package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.ShortProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.UpdateProtocolTemplateDto;
import ru.nabokovsg.templates.models.*;

import java.util.List;

public interface ProtocolTemplateService {

    ShortProtocolTemplateDto save(NewProtocolTemplateDto protocolDto);

    ShortProtocolTemplateDto update(UpdateProtocolTemplateDto protocolDto);

    ProtocolTemplateDto get(Long id);

    List<ShortProtocolTemplateDto> getAll();

    void addTable(Long id, TableTemplate table);

    void addSubsection(Long id, SubsectionTemplate subsection);

    void addCharacteristicsSurveyObject(Long id, List<CharacteristicsSurveyObject> characteristics);

    void addConclusion(Long id, ConclusionTemplate conclusion);

    void addAppendices(Long id, AppendicesTemplate appendices);

    void addRecommendation(Long id, RecommendationTemplate recommendation);
}