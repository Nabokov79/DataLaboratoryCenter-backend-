package ru.nabokovsg.templates.services;
import ru.nabokovsg.templates.models.DocumentationTemplate;

import java.util.List;

public interface DocumentationDataService {

    List<DocumentationTemplate> save(List<String> values);
}