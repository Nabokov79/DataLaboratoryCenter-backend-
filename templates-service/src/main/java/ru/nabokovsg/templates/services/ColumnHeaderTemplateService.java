package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.columns.NewColumnHeaderTemplateDto;
import ru.nabokovsg.templates.dto.columns.UpdateColumnHeaderTemplateDto;
import ru.nabokovsg.templates.models.ColumnHeaderTemplate;

import java.util.List;

public interface ColumnHeaderTemplateService {

    List<ColumnHeaderTemplate> save(List<NewColumnHeaderTemplateDto> columnHeaderTemplateDto);

    List<ColumnHeaderTemplate> update(List<UpdateColumnHeaderTemplateDto> columnHeaderTemplateDto);
}