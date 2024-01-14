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
import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;
import ru.nabokovsg.templates.models.enums.DataType;
import ru.nabokovsg.templates.services.SubsectionTemplateService;

@RestController
@RequestMapping(
        value = "/template/subsection",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон подраздела",
        description="API для работы с данными шаблона подраздела раздела")
public class SubsectionTemplateController {

    private final SubsectionTemplateService service;

    @Operation(summary = "Добавление нового шаблона подраздела раздела")
    @PostMapping("/section")
    public ResponseEntity<SubsectionTemplateDto> saveFromSectionTemplate(
            @RequestParam(name = "sectionId")
            @NotNull @Positive @Parameter(name = "Индентификатор раздела") Long sectionId,
            @RequestBody @Valid
            @Parameter(description = "Шаблон подраздела") NewSubsectionTemplateDto subsectionsDto) {
        return ResponseEntity.ok().body(service.save(DataType.SECTION, sectionId, subsectionsDto));
    }

    @Operation(summary = "Добавление нового шаблона подраздела протокола")
    @PostMapping("/protocol")
    public ResponseEntity<SubsectionTemplateDto> saveFromProtocolTemplate(
            @RequestParam(name = "protocolId")
            @NotNull @Positive @Parameter(name = "Индентификатор протокола") Long protocolId,
            @RequestBody @Valid
            @Parameter(description = "Шаблон подраздела") NewSubsectionTemplateDto subsectionsDto) {
        return ResponseEntity.ok().body(service.save(DataType.PROTOCOL, protocolId, subsectionsDto));
    }

    @Operation(summary = "Добавление нового шаблона подраздела протокола отчета")
    @PostMapping("/protocol/report")
    public ResponseEntity<SubsectionTemplateDto> saveFromProtocolReportTemplate(
            @RequestParam(name = "protocolId")
            @NotNull @Positive @Parameter(name = "Индентификатор протокола") Long protocolId,
            @RequestBody @Valid
            @Parameter(description = "Шаблон подраздела") NewSubsectionTemplateDto subsectionsDto) {
        return ResponseEntity.ok().body(service.save(DataType.PROTOCOL_REPORT, protocolId, subsectionsDto));
    }

    @Operation(summary = "Изменение данных шаблона подраздела")
    @PatchMapping
    public ResponseEntity<SubsectionTemplateDto> update(
                            @RequestBody @Valid
                            @Parameter(description = "Шаблон подраздела") UpdateSubsectionTemplateDto subsectionsDto) {
        return ResponseEntity.ok().body(service.update(subsectionsDto));
    }

    @Operation(summary = "Получить данные шаблона подраздела")
    @GetMapping("/{id}")
    public ResponseEntity<SubsectionTemplateDto> get(@PathVariable @NotNull @Positive
                                                     @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Удалить подраздел")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Подраздел удален.");
    }
}