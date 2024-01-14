package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.NewConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.UpdateConclusionTemplateDto;
import ru.nabokovsg.templates.models.enums.DataType;

public interface ConclusionTemplateService {

    ConclusionTemplateDto save(DataType type, NewConclusionTemplateDto conclusionDto);

    ConclusionTemplateDto update(UpdateConclusionTemplateDto conclusionDto);

    void delete(Long id);
}