package ru.nabokovsg.data.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.data.dto.documentation.DocumentationDto;
import ru.nabokovsg.data.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.data.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.data.dto.objectsType.ObjectsTypeDocumentationDto;
import ru.nabokovsg.data.services.DocumentationService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/object/type/data/documentations",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Нормативная документация",
     description="API для работы с данными нормативной документации")
public class DocumentationController {

    private final DocumentationService service;

    @Operation(summary = "Добавление нового нормативного документа")
    @PostMapping
    public ResponseEntity<List<ObjectsTypeDocumentationDto>> save(
                         @RequestParam("objectsTypeId") @NotNull @NotEmpty List<Long> objectsTypeId,
                         @RequestBody @Valid
                         @Parameter(description = "Нормативный документ") List<NewDocumentationDto> documentationsDto) {
        return ResponseEntity.ok().body(service.save(objectsTypeId,documentationsDto));
    }

    @Operation(summary = "Изменение данных нормативного документа")
    @PatchMapping
    public ResponseEntity<List<DocumentationDto>> update(
                      @RequestBody @Valid
                      @Parameter(description = "Нормативный документ") List<UpdateDocumentationDto> documentationsDto) {
        return ResponseEntity.ok().body(service.update(documentationsDto));
    }
}