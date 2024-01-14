package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.section.NewSectionTemplateDto;
import ru.nabokovsg.templates.dto.section.SectionTemplateDto;
import ru.nabokovsg.templates.dto.section.UpdateSectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.ShortSubsectionTemplateDto;
import ru.nabokovsg.templates.services.SectionTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/template/section",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон разделов отчета",
        description="API для работы с данными шаблонов разделов")
public class SectionTemplateController {

    private final SectionTemplateService service;

    @Operation(summary = "Добавить новые разделы")
    @PostMapping
    public ResponseEntity<List<SectionTemplateDto>> save(
                                                      @RequestParam(name = "reportId") @NotNull @Positive Long reportId
                                                    , @RequestBody @Valid List<NewSectionTemplateDto> sectionsDto) {
        return ResponseEntity.ok().body(service.save(reportId, sectionsDto));
    }

    @Operation(summary = "Изменение данных разделов")
    @PatchMapping
    public ResponseEntity<List<SectionTemplateDto>> update(
                                                       @RequestBody @Valid List<UpdateSectionTemplateDto> sectionsDto) {
        return ResponseEntity.ok().body(service.update(sectionsDto));
    }

    @Operation(summary = "Получить раздел")
    @GetMapping("/{id}")
    public ResponseEntity<SectionTemplateDto> get(@PathVariable @NotNull @Positive Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить подразделы раздела")
    @GetMapping("/{id}/subsection")
    public ResponseEntity<List<ShortSubsectionTemplateDto>> getAllSubsections(@PathVariable
                                                                              @NotNull @Positive Long id) {
        return ResponseEntity.ok().body(service.getAllSubsections(id));
    }
}