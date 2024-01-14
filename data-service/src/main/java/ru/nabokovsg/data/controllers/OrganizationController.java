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
import ru.nabokovsg.data.dto.organization.NewOrganizationDto;
import ru.nabokovsg.data.dto.organization.OrganizationDto;
import ru.nabokovsg.data.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.data.dto.organization.UpdateOrganizationDto;
import ru.nabokovsg.data.services.OrganizationService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/organizations",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Организация",
     description="API для работы с данными организации")
public class OrganizationController {

    private final OrganizationService service;

    @Operation(summary = "Добавление данных организации")
    @PostMapping
    public ResponseEntity<OrganizationDto> save(
                                           @RequestBody @Valid
                                           @Parameter(description = "Организация") NewOrganizationDto organizationDto) {
        return ResponseEntity.ok().body(service.save(organizationDto));
    }

    @Operation(summary = "Изменение данных организации")
    @PatchMapping
    public ResponseEntity<OrganizationDto> update(
                                        @RequestBody @Valid
                                        @Parameter(description = "Организация") UpdateOrganizationDto organizationDto) {
        return ResponseEntity.ok().body(service.update(organizationDto));
    }

    @Operation(summary = "Получение полных данных организации")
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> get(@PathVariable @NotNull @Positive
                                               @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение кратких сведений об организациях")
    @GetMapping
    public ResponseEntity<List<ShortOrganizationDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление данных организации")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("удалено");
    }
}