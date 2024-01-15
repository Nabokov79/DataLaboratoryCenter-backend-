package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.templates.models.DocumentationTemplate;

@Mapper(componentModel = "spring")
public interface DocumentationDataMapper {

    @Mapping(target = "id", ignore = true)
    DocumentationTemplate mapToDocumentationTemplate(String value);
}