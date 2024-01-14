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
import ru.nabokovsg.data.dto.surveyObject.NewSurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.ShortSurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.SurveyObjectDto;
import ru.nabokovsg.data.dto.surveyObject.UpdateSurveyObjectDto;
import ru.nabokovsg.data.services.SurveyObjectService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/objects",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Объект обследования",
        description="API для работы с данными объекта обследования")
public class SurveyObjectController {

    private final SurveyObjectService service;

    @Operation(summary = "Добавление сведений об объекте")
    @PostMapping
    public ResponseEntity<List<ShortSurveyObjectDto>> save(
                                  @RequestBody @Valid
                                  @Parameter(description = "Объект обследования") List<NewSurveyObjectDto> objectsDto) {
        return ResponseEntity.ok().body(service.save(objectsDto));
    }

    @Operation(summary = "Изменение сведений об объекте")
    @PatchMapping
    public ResponseEntity<List<ShortSurveyObjectDto>> update(
                               @RequestBody @Valid
                               @Parameter(description = "Объект обследования") List<UpdateSurveyObjectDto> objectsDto) {
        return ResponseEntity.ok().body(service.update(objectsDto));
    }

    @Operation(summary = "Изменение сведений об объекте")
    @GetMapping("/{id}")
    public ResponseEntity<SurveyObjectDto> get(@PathVariable @NotNull @Positive
                                               @Parameter(description = "Индентификатор объекта") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Изменение сведений об объекте")
    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<SurveyObjectDto>> getAll(@PathVariable @NotNull @Positive
                                               @Parameter(description = "Индентификатор объекта") Long buildingId) {
        return ResponseEntity.ok().body(service.getAll(buildingId));
    }

    @Operation(summary = "Удаление объекта")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор объекта") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Объект был удален");
    }
}