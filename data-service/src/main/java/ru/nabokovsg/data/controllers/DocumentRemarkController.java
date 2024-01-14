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
import ru.nabokovsg.data.dto.remark.DocumentRemarkDto;
import ru.nabokovsg.data.dto.remark.NewDocumentRemarkDto;
import ru.nabokovsg.data.dto.remark.UpdateDocumentRemarkDto;
import ru.nabokovsg.data.services.DocumentRemarkService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/reporting/document/remark",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Замечания по оформлению документа",
        description="API для работы с данными замечания по оформлению документа")
public class DocumentRemarkController {

    private final DocumentRemarkService service;

    @Operation(summary = "Изменение данных заявки")
    @PostMapping
    public ResponseEntity<DocumentRemarkDto> save(
                                         @RequestBody @Valid
                                         @Parameter(description = "Данные документа") NewDocumentRemarkDto remarkDto) {
        return ResponseEntity.ok().body(service.save(remarkDto));
    }

    @Operation(summary = "Изменение данных заявки")
    @PatchMapping
    public ResponseEntity<DocumentRemarkDto> update(
                                       @RequestBody @Valid
                                       @Parameter(description = "Данные документа") UpdateDocumentRemarkDto remarkDto) {
        return ResponseEntity.ok().body(service.update(remarkDto));
    }

    @Operation(summary = "Получить все замечания")
    @GetMapping
    public ResponseEntity<List<DocumentRemarkDto>> getAll(
            @RequestParam(value = "employeeId", required = false)
            @Parameter(description = "Индентификатор пользователя, оставившего замечание") Long employeeId,
            @RequestParam(value = "employeeDocumentId", required = false)
            @Parameter(description = "Индентификатор пользователя, оформившего документ") Long employeeDocumentId,
            @RequestParam(value = "employeeDrawingId", required = false)
            @Parameter(description = "Индентификатор пользователя оформившего чертеж") Long employeeDrawingId) {
        return ResponseEntity.ok().body(service.getAll(employeeId,employeeDocumentId, employeeDrawingId));
    }

    @Operation(summary = "Удаление замечания")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор замечания") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Замечание удалено");
    }
}