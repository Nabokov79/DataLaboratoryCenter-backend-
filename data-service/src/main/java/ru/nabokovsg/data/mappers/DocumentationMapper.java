package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.documentation.DocumentationDto;
import ru.nabokovsg.data.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.data.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.data.models.Documentation;

@Mapper(componentModel = "spring")
public interface DocumentationMapper {

    Documentation mapToNewDocumentations(NewDocumentationDto documentationDto);

    Documentation mapToUpdateDocumentation(UpdateDocumentationDto documentationDto);

    DocumentationDto mapToDocumentationDto(Documentation documentation);
}