package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.models.DocumentationTemplate;

@Mapper(componentModel = "spring")
public interface DocumentationDataMapper {

    DocumentationTemplate mapToDocumentationTemplate(String value);
}