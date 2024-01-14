package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;
import ru.nabokovsg.templates.models.TableTemplate;
import ru.nabokovsg.templates.models.enums.DataType;

public interface SubsectionTemplateService {

    SubsectionTemplateDto save(DataType type, Long id, NewSubsectionTemplateDto subsectionDto);

    SubsectionTemplateDto update(UpdateSubsectionTemplateDto subsectionsDto);

    SubsectionTemplateDto get(Long id);

    void delete(Long id);

    void addTable(Long id, TableTemplate table);
}