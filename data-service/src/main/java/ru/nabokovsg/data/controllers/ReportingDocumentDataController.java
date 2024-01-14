package ru.nabokovsg.data.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.data.dto.reportingDocumentData.*;
import ru.nabokovsg.data.services.ReportingDocumentDataService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/reporting",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Заявка",
        description="API для работы с данными заявки")
public class ReportingDocumentDataController {

    private final ReportingDocumentDataService service;

    @Operation(summary = "Изменение данных заявки")
    @PatchMapping
    public ResponseEntity<List<ReportingDocumentDataDto>> update(
                            @RequestBody @Valid
                            @Parameter(description = "Данные документа") List<UpdateReportingDocumentDataDto> dataDto) {
        return ResponseEntity.ok().body(service.update(dataDto));
    }

    @Operation(summary = "Получение данных заявки")
    @GetMapping
    public ResponseEntity<List<ReportingDocumentDataDto>> getAll(
            @RequestParam(value = "surveyObjectId", required = false)
            @Parameter(description = "Индентификатор объекта обследования") Long surveyObjectId,
            @RequestParam(value = "startDate", required = false)
            @Parameter(description = "Начало периода") LocalDate startPeriod,
            @RequestParam(value = "endDate", required = false)
            @Parameter(description = "Конец периода") LocalDate endPeriod,
            @RequestParam(value = "Дата передачи доумента", required = false)
            @Parameter(description = "Индентификатор адреса") LocalDate transferDate,
            @RequestParam(value = "employeeDocumentId", required = false)
            @Parameter(description = "Индентификатор сотрудника, выполнившего документ") Long employeeDocumentId,
            @RequestParam(value = "employeeDrawingId", required = false)
            @Parameter(description = "Индентификатор сотрудника, выполнившего чертеж") Long employeeDrawingId,
            @RequestParam(value = "documentStatus", required = false)
            @Parameter(description = "Статус документа") String documentStatus,
            @RequestParam(value = "drawingStatus", required = false)
            @Parameter(description = "Статус чертежа") String drawingStatus) {
        return ResponseEntity.ok().body(service.getAll(
                new ReportingDocumentDataSearchParametersDto(surveyObjectId, startPeriod,
                                                          endPeriod, transferDate,
                                                          employeeDocumentId, employeeDrawingId,
                                                           documentStatus, drawingStatus)));
    }

    @Operation(summary = "Сохранить данные документа")
    @PostMapping("/document")
    public ResponseEntity<HttpStatus> saveDocumentData(
                                                @RequestBody @Valid
                                                @Parameter(description = "Данные документа") DocumentDataDto pathDto) {
        service.saveDocumentData(pathDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Сохранить данные чертежа")
    @PostMapping("/drawing")
    public ResponseEntity<HttpStatus> saveDrawingData(
                                                  @RequestBody @Valid
                                                  @Parameter(description = "Данные документа") DrawingDataDto pathDto) {
        service.saveDrawingData(pathDto);
        return ResponseEntity.ok().build();
    }
}