package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.table.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.table.TableTemplateDto;
import ru.nabokovsg.templates.dto.table.UpdateTableTemplateDto;
import ru.nabokovsg.templates.models.enums.DataType;

public interface TableTemplateService {

    TableTemplateDto save(DataType type, Long id, NewTableTemplateDto tableDto);

    TableTemplateDto update(UpdateTableTemplateDto tableDto);

    TableTemplateDto get(Long id);

    void delete(Long id);
}