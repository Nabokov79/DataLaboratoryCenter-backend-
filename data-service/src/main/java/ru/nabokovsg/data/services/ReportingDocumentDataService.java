package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.reportingDocumentData.*;
import ru.nabokovsg.data.models.Application;
import ru.nabokovsg.data.models.ReportingDocumentData;

import java.util.List;

public interface ReportingDocumentDataService {

    void save(List<Application> applications);

    List<ReportingDocumentDataDto> update(List<UpdateReportingDocumentDataDto> dataDto);

    List<ReportingDocumentDataDto> getAll(ReportingDocumentDataSearchParametersDto parameters);

    ReportingDocumentData getById(Long id);

    void saveDocumentData(DocumentDataDto pathDto);

    void saveDrawingData(DrawingDataDto pathDto);
}