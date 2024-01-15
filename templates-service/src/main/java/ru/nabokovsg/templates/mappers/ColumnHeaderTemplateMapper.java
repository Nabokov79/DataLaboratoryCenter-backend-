package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.nabokovsg.templates.dto.columns.NewColumnHeaderTemplateDto;
import ru.nabokovsg.templates.dto.columns.UpdateColumnHeaderTemplateDto;
import ru.nabokovsg.templates.models.ColumnHeaderTemplate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ColumnHeaderTemplateMapper {

    ColumnHeaderTemplate mapToNewColumnHeaderTemplates(NewColumnHeaderTemplateDto templateDto);

    ColumnHeaderTemplate mapToUpdateColumnHeaderTemplates(UpdateColumnHeaderTemplateDto templateDto);
}