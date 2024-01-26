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
import ru.nabokovsg.data.dto.surveyObjectElementData.rejection.ElementRejectionParametersDto;
import ru.nabokovsg.data.dto.surveyObjectElementData.rejection.NewElementRejectionParametersDto;
import ru.nabokovsg.data.dto.surveyObjectElementData.rejection.UpdateElementRejectionParametersDto;
import ru.nabokovsg.data.services.ElementRejectionParametersService;

@RestController
@RequestMapping(
        value = "/data/objects/elements/rejection",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Параметры браковки элемента объекта",
        description="API для работы с данными параметров браковки элементов")
public class ElementRejectionParametersController {

    private final ElementRejectionParametersService service;

    @Operation(summary = "Добавление новых параметров браковки")
    @PostMapping
    public ResponseEntity<ElementRejectionParametersDto> save(
            @RequestBody @Valid
            @Parameter(description = "Параметры браковки") NewElementRejectionParametersDto rejectionParametersDto) {
        return ResponseEntity.ok().body(service.save(rejectionParametersDto));
    }

    @Operation(summary = "Изменение параметров браковки")
    @PatchMapping
    public ResponseEntity<ElementRejectionParametersDto> update(
            @RequestBody @Valid
            @Parameter(description = "Параметры браковки") UpdateElementRejectionParametersDto rejectionParametersDto) {
        return ResponseEntity.ok().body(service.update(rejectionParametersDto));
    }

    @Operation(summary = "Получить параметры браковки элемента")
    @GetMapping("/{id}")
    public ResponseEntity<ElementRejectionParametersDto> get(@PathVariable @NotNull @Positive
                                               @Parameter(description = "Индентификатор элемента") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Удалить параметры браковки элемента")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор параметров") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Удалены норму браковки елемента.");
    }
}