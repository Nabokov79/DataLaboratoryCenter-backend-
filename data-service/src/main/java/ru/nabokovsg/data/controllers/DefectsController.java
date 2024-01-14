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
import ru.nabokovsg.data.dto.defect.DefectDto;
import ru.nabokovsg.data.dto.defect.NewDefectDto;
import ru.nabokovsg.data.dto.defect.UpdateDefectDto;
import ru.nabokovsg.data.dto.objectsType.ObjectsTypeDefectDto;
import ru.nabokovsg.data.services.DefectsService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/object/type/data/elements/defects",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Дефекты элемента объекта",
        description="API для работы с дефектами элементов объекта")
public class DefectsController {

    private final DefectsService service;

    @Operation(summary = "Добавление новых дефектов объекта")
    @PostMapping
    public ResponseEntity<List<ObjectsTypeDefectDto>> save(
            @RequestParam("objectsTypeId") @NotNull @NotEmpty List<Long> objectsTypeId,
            @RequestBody @Valid @Parameter(description = "Дефекты элемента") List<NewDefectDto> defectsDto) {
        return ResponseEntity.ok().body(service.save(objectsTypeId, defectsDto));
    }

    @Operation(summary = "Изменение данных дефектов объекта")
    @PatchMapping
    public ResponseEntity<List<DefectDto>> update(
            @RequestBody @Valid @Parameter(description = "Дефекты элемента") List<UpdateDefectDto> defectsDto) {
        return ResponseEntity.ok().body(service.update(defectsDto));
    }
}