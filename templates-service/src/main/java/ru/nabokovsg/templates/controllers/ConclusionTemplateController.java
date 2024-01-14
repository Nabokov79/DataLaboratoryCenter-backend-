package ru.nabokovsg.templates.controllers;

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
import ru.nabokovsg.templates.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.NewConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.UpdateConclusionTemplateDto;
import ru.nabokovsg.templates.models.enums.DataType;
import ru.nabokovsg.templates.services.ConclusionTemplateService;

@RestController
@RequestMapping(
        value = "/template/conclusion",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
@Tag(name="Шаблон заключений",
        description="API для работы с данными шаблона заключений к протоколам")
public class ConclusionTemplateController {

    private final ConclusionTemplateService service;

    @Operation(summary = "Данные шаблона новых заключений протокола отчета")
    @PostMapping("/report/protocol")
    public ResponseEntity<ConclusionTemplateDto> saveFromReportProtocolTemplate(
            @RequestBody @Valid @Parameter(name = "Шаблон заключения") NewConclusionTemplateDto conclusionDto) {
        return ResponseEntity.ok().body(service.save(DataType.PROTOCOL_REPORT, conclusionDto));
    }

    @Operation(summary = "Данные шаблона новых заключений протокола")
    @PostMapping("/protocol")
    public ResponseEntity<ConclusionTemplateDto> saveFromProtocolTemplate(
            @RequestBody @Valid @Parameter(name = "Шаблон заключения") NewConclusionTemplateDto conclusionDto) {
        return ResponseEntity.ok().body(service.save(DataType.PROTOCOL, conclusionDto));
    }

    @Operation(summary = "Изменение данных шаблона заключений")
    @PatchMapping
    public ResponseEntity<ConclusionTemplateDto> update(@RequestBody @Valid UpdateConclusionTemplateDto conclusionDto) {
        return ResponseEntity.ok().body(service.update(conclusionDto));
    }

    @Operation(summary = "Удалить заключения")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Рекомендация удалена.");
    }
}