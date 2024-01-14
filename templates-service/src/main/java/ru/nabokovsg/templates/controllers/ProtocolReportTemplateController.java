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
import ru.nabokovsg.templates.dto.protocolReport.NewProtocolReportTemplateDto;
import ru.nabokovsg.templates.dto.protocolReport.ProtocolReportTemplateDto;
import ru.nabokovsg.templates.dto.protocolReport.ShortProtocolReportTemplateDto;
import ru.nabokovsg.templates.dto.protocolReport.UpdateProtocolReportTemplateDto;
import ru.nabokovsg.templates.services.ProtocolReportTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/template/report/protocol",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон протокола отчета",
        description="API для работы с данными шаблона протокола отчета")
public class ProtocolReportTemplateController {

    private final ProtocolReportTemplateService service;

    @Operation(summary = "Данные шаблона нового протокола/заключения")
    @PostMapping
    public ResponseEntity<ShortProtocolReportTemplateDto> save(
                                          @RequestParam(name = "sectionId")
                                          @NotNull @Positive @Parameter(name = "Индентификатор раздела") Long sectionId,
                                          @RequestBody @Valid NewProtocolReportTemplateDto protocolDto) {
        return ResponseEntity.ok().body(service.save(sectionId, protocolDto));
    }

    @Operation(summary = "Данные для изменения информации в протокола/заключения")
    @PatchMapping
    public ResponseEntity<ShortProtocolReportTemplateDto> update(@RequestBody @Valid UpdateProtocolReportTemplateDto protocolDto) {
        return ResponseEntity.ok().body(service.update(protocolDto));
    }

    @Operation(summary = "Получить данные протоколов/заключений")
    @GetMapping("/{id}")
    public ResponseEntity<ProtocolReportTemplateDto> get(@PathVariable @NotNull @Positive Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить краткие данные протоколов/заключений")
    @GetMapping("/all")
    public ResponseEntity<List<ShortProtocolReportTemplateDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
}