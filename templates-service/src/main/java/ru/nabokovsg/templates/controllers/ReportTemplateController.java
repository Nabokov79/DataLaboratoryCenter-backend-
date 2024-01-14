package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.pageTitle.ShortPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.report.ReportTemplateDto;
import ru.nabokovsg.templates.dto.section.ShortSectionTemplateDto;
import ru.nabokovsg.templates.services.ReportTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/template/report",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон отчета",
        description="API для работы с данными шаблона отчета")
public class ReportTemplateController {

    private final ReportTemplateService service;

    @Operation(summary = "Получить шаблон отчета")
    @GetMapping
    public ResponseEntity<ReportTemplateDto> get(
            @RequestParam(name = "id", required = false) @Parameter(name = "Индентификатор раздела") Long id,
            @RequestParam(name = "objectTypeId", required = false) @Parameter(name = "Индентификатор раздела") Long objectTypeId,
            @RequestParam(name = "reportingDocumentId", required = false) @Parameter(name = "Индентификатор раздела") Long reportingDocumentId
    ) {
        return ResponseEntity.ok().body(service.get(id, objectTypeId, reportingDocumentId));
    }

    @Operation(summary = "Получить все отчеты")
    @GetMapping("/all")
    public ResponseEntity<List<ShortPageTitleTemplateDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Получить разделы по индентификатору отчета")
    @GetMapping("/{id}/section")
    public ResponseEntity<List<ShortSectionTemplateDto>> getAllSections(
                                                                @PathVariable @NotNull @Positive
                                                                @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.getAllSections(id));
    }
}