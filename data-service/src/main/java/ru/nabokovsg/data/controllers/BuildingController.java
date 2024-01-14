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
import ru.nabokovsg.data.dto.building.BuildingDto;
import ru.nabokovsg.data.dto.building.NewBuildingDto;
import ru.nabokovsg.data.dto.building.UpdateBuildingDto;
import ru.nabokovsg.data.services.BuildingService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/department/building",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Строение(энергетический источник)",
        description="API для работы с информацией о строении")
public class BuildingController {

    private final BuildingService service;

    @Operation(summary = "Добавление новой информации о строении")
    @PostMapping
    public ResponseEntity<List<BuildingDto>> save(
                                              @RequestBody @Valid
                                              @Parameter(description = "Строение") List<NewBuildingDto> buildingsDto) {
        return ResponseEntity.ok().body(service.save(buildingsDto));
    }

    @Operation(summary = "Изменение данных")
    @PatchMapping
    public ResponseEntity<List<BuildingDto>> update(
                                           @RequestBody @Valid
                                           @Parameter(description = "Строение") List<UpdateBuildingDto> buildingsDto) {
        return ResponseEntity.ok().body(service.update(buildingsDto));
    }

    @Operation(summary = "Получение данных")
    @GetMapping
    public ResponseEntity<List<BuildingDto>> getAll(
                       @RequestParam @NotNull @Positive
                       @Parameter(description = "Индентификатор подразделения филиала организации") Long departmentId) {
        return ResponseEntity.ok().body(service.getAll(departmentId));
    }

    @Operation(summary = "Удаление данных строения")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Информация удалена");
    }
}
