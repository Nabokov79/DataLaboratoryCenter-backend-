package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.mappers.DocumentationDataMapper;
import ru.nabokovsg.templates.models.DocumentationTemplate;
import ru.nabokovsg.templates.repository.DocumentationTemplateRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentationDataServiceImpl implements DocumentationDataService {

    private final DocumentationTemplateRepository repository;
    private final DocumentationDataMapper mapper;
    @Override
    public List<DocumentationTemplate> save(List<String> values) {
        Map<String, DocumentationTemplate> documentationsData = repository.findAllByValueDataList(values)
                .stream()
                .collect(Collectors.toMap(DocumentationTemplate::getValue, s -> s));
        values = values.stream().filter(v -> !documentationsData.containsKey(v)).toList();
        if (!values.isEmpty()) {
            repository.saveAll(values.stream()
                    .map(mapper::mapToDocumentationTemplate)
                    .toList()).forEach(k -> documentationsData.put(k.getValue(), k));
        }
        return documentationsData.values().stream().toList();
    }
}