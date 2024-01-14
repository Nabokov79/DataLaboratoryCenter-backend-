package ru.nabokovsg.templates.services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.DocumentationDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.MeasuringToolDataDto;
import ru.nabokovsg.templates.models.DocumentationTemplate;
import ru.nabokovsg.templates.models.MeasuringToolTemplate;

import java.util.List;

public interface SubsectionDataProcessingService {

    List<DocumentationTemplate> getDocumentationData(@Valid DocumentationDataDto documentationDataDto);

    List<MeasuringToolTemplate> getMeasuringToolData(@Valid List<MeasuringToolDataDto> measuringToolDataDto);

    String getDivisionData(@Valid DivisionDataDto divisionDataDto);

    String getCertificateData(@NotNull @Positive Long employeeId);
}