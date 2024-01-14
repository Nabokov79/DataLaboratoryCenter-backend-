package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.templates.dto.header.UpdateHeaderTemplateDto;
import ru.nabokovsg.templates.models.HeaderTemplate;

public interface HeaderTemplateService {

    HeaderTemplate save(NewHeaderTemplateDto headerDto);

    HeaderTemplate update(UpdateHeaderTemplateDto headerDto);
}