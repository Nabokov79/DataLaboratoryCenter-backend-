package ru.nabokovsg.data.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.data.dto.element.ElementDto;
import ru.nabokovsg.data.dto.element.NewElementDto;
import ru.nabokovsg.data.dto.element.UpdateElementDto;
import ru.nabokovsg.data.dto.objectsType.ObjectsTypeElementsDto;
import ru.nabokovsg.data.services.ElementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/object/type/elements",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Элемент объекта",
        description="API для работы с данными елемента объекта")
public class ElementController {

    private final ElementService service;

    @Operation(summary = "Добавление новых элементов объекта")
    @PostMapping
    public ResponseEntity<List<ObjectsTypeElementsDto>> save(
                                        @RequestParam("objectsTypeId") @NotNull @NotEmpty List<Long> objectsTypeId,
                                        @RequestBody @Valid
                                        @Parameter(description = "Список элементов") List<NewElementDto> elementsDto) {
        return ResponseEntity.ok().body(service.save(objectsTypeId, elementsDto));
    }

    @Operation(summary = "Изменение данных элементов объекта")
    @PatchMapping
    public ResponseEntity<List<ElementDto>> update(
                                     @RequestBody @Valid
                                     @Parameter(description = "Список элементов") List<UpdateElementDto> elementsDto) {
        return ResponseEntity.ok().body(service.update(elementsDto));
    }

    @Operation(summary = "Изменение данных элементов объекта")
    @GetMapping("/{objectsTypeId}")
    public ResponseEntity<ObjectsTypeElementsDto> getAll(
                                          @PathVariable @NotNull @Positive
                                          @Parameter(description = "Индентификатор типа объекта") Long objectsTypeId) {
        return ResponseEntity.ok().body(service.getAll(objectsTypeId));
    }
}