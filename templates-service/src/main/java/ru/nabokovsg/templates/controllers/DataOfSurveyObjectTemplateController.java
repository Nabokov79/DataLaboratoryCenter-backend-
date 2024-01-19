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
import ru.nabokovsg.templates.dto.passportData.DataOfSurveyObjectTemplateDto;
import ru.nabokovsg.templates.dto.passportData.NewDataOfSurveyObjectTemplate;
import ru.nabokovsg.templates.dto.passportData.UpdateDataOfSurveyObjectTemplate;
import ru.nabokovsg.templates.services.DataOfSurveyObjectTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/template/passport",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
@Tag(name="Шаблон данных пасопрта объекта",
        description="API для работы с шаблоном данных паспорта объекта")
public class DataOfSurveyObjectTemplateController {

    private final DataOfSurveyObjectTemplateService service;

    @Operation(summary = "Данные шаблона новых характеристик объекта")
    @PostMapping
    public ResponseEntity<List<DataOfSurveyObjectTemplateDto>> save(
            @RequestParam(name = "objectTypeId")
            @NotNull @Positive @Parameter(name = "Индентификатор типа объекта") Long objectTypeId,
            @RequestBody @Valid
            @Parameter(name = "Шаблон данных паспорта") List<NewDataOfSurveyObjectTemplate> characteristicsDto) {
        return ResponseEntity.ok().body(service.save(objectTypeId, characteristicsDto));
    }

    @Operation(summary = "Изменение данных характеристик объекта")
    @PatchMapping
    public ResponseEntity<List<DataOfSurveyObjectTemplateDto>> update(
            @RequestParam(name = "objectTypeId")
            @NotNull @Positive @Parameter(name = "Индентификатор типа объекта") Long objectTypeId,
            @RequestBody @Valid
            @Parameter(name = "Шаблон данных паспорта") List<UpdateDataOfSurveyObjectTemplate> characteristicsDto) {
        return ResponseEntity.ok().body(service.update(objectTypeId, characteristicsDto));
    }

    @Operation(summary = "Получение всех шаблонов данных паспорта объекта")
    @GetMapping
    public ResponseEntity<List<DataOfSurveyObjectTemplateDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удалить шаблон")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Характеристика удалена.");
    }
}