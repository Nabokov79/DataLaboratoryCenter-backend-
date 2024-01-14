package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.NewAppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.UpdateAppendicesTemplateDto;
import ru.nabokovsg.templates.models.enums.DataType;

public interface AppendicesTemplateService {

    AppendicesTemplateDto save(DataType type, NewAppendicesTemplateDto appendicesDto);

    AppendicesTemplateDto update(UpdateAppendicesTemplateDto appendicesDto);

    void delete(Long id);
}