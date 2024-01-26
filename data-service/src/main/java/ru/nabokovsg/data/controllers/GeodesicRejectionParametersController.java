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
import ru.nabokovsg.data.dto.rejection.surveyObject.GeodesicRejectionParametersDto;
import ru.nabokovsg.data.dto.rejection.surveyObject.NewGeodesicRejectionParametersDto;
import ru.nabokovsg.data.dto.rejection.surveyObject.UpdateGeodesicRejectionParametersDto;
import ru.nabokovsg.data.services.GeodesicRejectionParametersService;

import java.util.List;


@RestController
@RequestMapping(
        value = "/data/objects/rejection",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Параметры браковки объекта по результатм невелирования",
        description="API для работы с данными параметров браковки объекта по результатам нивелирования")
public class GeodesicRejectionParametersController {

    private final GeodesicRejectionParametersService service;

    @Operation(summary = "Добавление новых параметров браковки")
    @PostMapping
    public ResponseEntity<GeodesicRejectionParametersDto> save(
            @RequestBody @Valid
            @Parameter(description = "Параметры браковки") NewGeodesicRejectionParametersDto rejectionParametersDto) {
        return ResponseEntity.ok().body(service.save(rejectionParametersDto));
    }

    @Operation(summary = "Добавление новых параметров браковки")
    @PostMapping
    public ResponseEntity<List<GeodesicRejectionParametersDto>> addExisting(
                                               @RequestParam(name = "firstSurveyObjectId") Long firstSurveyObjectId
                                            ,  @RequestParam(name = "secondSurveyObjectId") Long secondSurveyObjectId) {
        return ResponseEntity.ok().body(service.addExisting(firstSurveyObjectId, secondSurveyObjectId));
    }

    @Operation(summary = "Изменение параметров браковки")
    @PatchMapping
    public ResponseEntity<GeodesicRejectionParametersDto> update(
           @RequestBody @Valid
           @Parameter(description = "Параметры браковки") UpdateGeodesicRejectionParametersDto rejectionParametersDto) {
        return ResponseEntity.ok().body(service.update(rejectionParametersDto));
    }

    @Operation(summary = "Получить параметры браковки объекта")
    @GetMapping("/{id}")
    public ResponseEntity<GeodesicRejectionParametersDto> get(@PathVariable @NotNull @Positive
                                                           @Parameter(description = "Индентификатор объекта") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Удалить параметры браковки объекта")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор параметров") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Удалены нормы браковки объекта.");
    }
}
