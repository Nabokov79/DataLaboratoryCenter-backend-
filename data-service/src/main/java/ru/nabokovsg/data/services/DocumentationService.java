package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.documentation.DocumentationDto;
import ru.nabokovsg.data.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.data.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.data.dto.objectsType.ObjectsTypeDocumentationDto;

import java.util.List;

public interface DocumentationService {

    List<ObjectsTypeDocumentationDto> save(List<Long> objectsTypeId, List<NewDocumentationDto> documentationsDto);

    List<DocumentationDto> update(List<UpdateDocumentationDto> documentationsDto);
}