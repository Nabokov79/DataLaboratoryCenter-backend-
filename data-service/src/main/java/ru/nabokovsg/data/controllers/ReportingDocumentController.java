package ru.nabokovsg.data.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.data.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.data.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.data.dto.reportingDocument.UpdateReportingDocumentDto;
import ru.nabokovsg.data.models.enums.DocumentType;
import ru.nabokovsg.data.services.ReportingDocumentService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/reporting/document",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Тип документа",
        description="API для работы с данными типа документа")
public class ReportingDocumentController {

    private final ReportingDocumentService service;

    @Operation(summary = "Добавление новых типа отчета")
    @PostMapping("/report")
    public ResponseEntity<List<ReportingDocumentDto>> saveReport(
              @RequestBody @Valid
              @Parameter(description = "Типы отчетного документа") List<NewReportingDocumentDto>documentDto) {
        return ResponseEntity.ok().body(service.save(DocumentType.REPORT,documentDto));
    }

    @Operation(summary = "Добавление новых типа протокола/заключения")
    @PostMapping("/protocol")
    public ResponseEntity<List<ReportingDocumentDto>> saveProtocol(
            @RequestBody @Valid
            @Parameter(description = "Типы отчетного документа") List<NewReportingDocumentDto> documentDto) {
        return ResponseEntity.ok().body(service.save(DocumentType.PROTOCOL,documentDto));
    }

    @Operation(summary = "Изменение данных типов отчетных документа")
    @PatchMapping
    public ResponseEntity<List<ReportingDocumentDto>> update(
           @RequestBody @Valid
           @Parameter(description = "Типы отчетного документа") List<UpdateReportingDocumentDto> documentDto) {
        return ResponseEntity.ok().body(service.update(documentDto));
    }

    @Operation(summary = "Получение данных типов отчетных документов")
    @GetMapping("/{id}")
    public ResponseEntity<ReportingDocumentDto> get(
                                               @PathVariable @NotNull @Positive
                                               @Parameter(description = "Индентификатор отчетного документа") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данных типов отчетных документов")
    @GetMapping
    public ResponseEntity<List<ReportingDocumentDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление типа отчетного документа")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор отчетного документа") Long id) {
        service.delete(id);
        return ResponseEntity.ok("удалено");
    }
}