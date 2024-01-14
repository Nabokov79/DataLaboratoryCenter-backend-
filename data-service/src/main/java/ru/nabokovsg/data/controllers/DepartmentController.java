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
import ru.nabokovsg.data.dto.department.DepartmentDto;
import ru.nabokovsg.data.dto.department.NewDepartmentDto;
import ru.nabokovsg.data.dto.department.ShortDepartmentDto;
import ru.nabokovsg.data.dto.department.UpdateDepartmentDto;
import ru.nabokovsg.data.services.DepartmentService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/organizations/branch/department",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Подразделение филиала организации",
        description="API для работы с данными подразделения филиала организации")
public class DepartmentController {

    private final DepartmentService service;

    @Operation(summary = "Добавление данных подразделения филиала организации")
    @PostMapping
    public ResponseEntity<DepartmentDto> save(
                                @RequestBody @Valid
                                @Parameter(description = "Подразделение") NewDepartmentDto departmentDto) {
        return ResponseEntity.ok().body(service.save(departmentDto));
    }

    @Operation(summary = "Изменение данных подразделения филиала организации")
    @PatchMapping
    public ResponseEntity<DepartmentDto> update(
                             @RequestBody @Valid
                             @Parameter(description = "Подразделение") UpdateDepartmentDto departmentDto) {
        return ResponseEntity.ok().body(service.update(departmentDto));
    }

    @Operation(summary = "Получение данных подразделения филиала организации")
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> get(@PathVariable @NotNull @Positive
                                             @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение кратких сведений о подразделении филиала организации")
    @GetMapping
    public ResponseEntity<List<ShortDepartmentDto>> getAll(
                                        @RequestParam @NotNull @Positive
                                        @Parameter(description = "Индентификатор филиала организации") Long branchId) {
        return ResponseEntity.ok().body(service.getAll(branchId));
    }

    @Operation(summary = "Удаление данных подразделения филиала организации")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("удалено");
    }
}