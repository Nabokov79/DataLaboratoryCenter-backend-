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
import ru.nabokovsg.templates.dto.characteristics.CharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.NewCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.UpdateCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.services.CharacteristicsSurveyObjectService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/template/characteristics",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
@Tag(name="Шаблон характеристики объекта",
        description="API для работы с данными шаблона характеристики объекта")
public class CharacteristicsSurveyObjectController {

    private final CharacteristicsSurveyObjectService service;

    @Operation(summary = "Данные шаблона новых характеристик объекта")
    @PostMapping
    public ResponseEntity<List<CharacteristicsSurveyObjectDto>> save(
            @RequestParam(name = "objectTypeId")
            @NotNull @Positive @Parameter(name = "Индентификатор типа объекта") Long objectTypeId,
            @RequestBody @Valid
            @Parameter(name = "Характеристики объекта") List<NewCharacteristicsSurveyObjectDto> characteristicsDto) {
        return ResponseEntity.ok().body(service.save(objectTypeId, characteristicsDto));
    }

    @Operation(summary = "Изменение данных характеристик объекта")
    @PatchMapping
    public ResponseEntity<List<CharacteristicsSurveyObjectDto>> update(
            @RequestParam(name = "objectTypeId")
            @NotNull @Positive @Parameter(name = "Индентификатор типа объекта") Long objectTypeId,
            @RequestBody @Valid
            @Parameter(name = "Характеристики объекта") List<UpdateCharacteristicsSurveyObjectDto> characteristicsDto) {
        return ResponseEntity.ok().body(service.update(objectTypeId, characteristicsDto));
    }

    @Operation(summary = "Получение всехшаблонов характеристик объекта")
    @GetMapping
    public ResponseEntity<List<CharacteristicsSurveyObjectDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удалить заключения")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Характеристика удалена.");
    }
}