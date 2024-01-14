package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.documentation.DocumentationDto;
import ru.nabokovsg.data.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.data.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.data.dto.objectsType.ObjectsTypeDocumentationDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.DocumentationMapper;
import ru.nabokovsg.data.models.Documentation;
import ru.nabokovsg.data.repository.DocumentationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentationServiceImpl implements DocumentationService {

    private final DocumentationRepository repository;
    private final DocumentationMapper mapper;
    private final ObjectsTypeService objectsTypeService;

    @Override
    public List<ObjectsTypeDocumentationDto> save(List<Long> objectsTypeId
                                                , List<NewDocumentationDto> documentationsDto) {
        Map<String, Documentation> documentations = repository.findAllByTitle(documentationsDto
                                                                    .stream()
                                                                    .map(NewDocumentationDto::getTitle).toList())
                                                            .stream()
                                                            .collect(Collectors.toMap(Documentation::getTitle, d -> d));
        List<Documentation> documentationsDb = documentationsDto.stream()
                                                                .map(mapper::mapToNewDocumentations)
                                                                .toList();
        if (documentations.isEmpty()) {
            return objectsTypeService.addDocumentations(objectsTypeId, repository.saveAll(documentationsDb));
        } else {
            documentationsDb =  repository.saveAll(documentationsDb
                                                                .stream()
                                                                .filter(d -> !documentations.containsKey(d.getTitle()))
                                                                .toList());
            documentationsDb.addAll(documentations.values());
            return objectsTypeService.addDocumentations(objectsTypeId, documentationsDb);
        }
    }

    @Override
    public List<DocumentationDto> update(List<UpdateDocumentationDto> documentationsDto) {
        validateIds(documentationsDto.stream().map(UpdateDocumentationDto::getId).toList());
        return repository.saveAll(documentationsDto.stream()
                                                    .map(mapper::mapToUpdateDocumentation)
                                                    .toList())
                                                    .stream().
                                                    map(mapper::mapToDocumentationDto)
                                                    .toList();
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Documentation> documentations = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Documentation::getId, subheading -> subheading));
        if (documentations.size() != ids.size() || documentations.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(documentations.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("documentations with ids= %s not found", ids));
        }
    }
}