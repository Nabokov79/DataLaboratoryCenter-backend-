package ru.nabokovsg.data.dto.reportingDocumentData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class ReportingDocumentDataSearchParametersDto {

    private Long surveyObjectId;
    private LocalDate startPeriod;
    private LocalDate endPeriod;
    private LocalDate transferDate;
    private Long employeeDocumentId;
    private Long employeeDrawingId;
    private String documentStatus;
    private String drawingStatus;
}