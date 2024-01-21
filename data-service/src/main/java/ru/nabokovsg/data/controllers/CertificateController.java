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
import ru.nabokovsg.data.dto.certificate.CertificateDto;
import ru.nabokovsg.data.dto.certificate.NewCertificateDto;
import ru.nabokovsg.data.dto.certificate.UpdateCertificateDto;
import ru.nabokovsg.data.services.CertificateService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/certificates",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Сертификаты сотрудников",
     description="API для работы с данными аттестации сотрудника")
public class CertificateController {

    private final CertificateService service;

    @Operation(summary = "Добавление данных сертификатов сотрудника")
    @PostMapping
    public ResponseEntity<List<CertificateDto>> save(
            @RequestBody @Valid
            @Parameter(description = "Список сертификатов сотрудника")List<NewCertificateDto> certificatesDto) {
        return ResponseEntity.ok().body(service.save(certificatesDto));
    }

    @Operation(summary = "Изменение данных аттестации сотрудника")
    @PatchMapping
    public ResponseEntity<List<CertificateDto>> update(
            @RequestBody @Valid
            @Parameter(description = "Список сертификатов сотрудника") List<UpdateCertificateDto> certificatesDto) {
        return ResponseEntity.ok().body(service.update(certificatesDto));
    }

    @Operation(summary = "Получение данных сертификатов сотрудников")
    @GetMapping
    public ResponseEntity<List<CertificateDto>> getAll(@RequestParam(required = false)
                                                      @Parameter(description = "Индентификатор сотрудника") Long employeeId,
                                                      @RequestParam(required = false)
                                                      @Parameter(description = "Дата") LocalDate date) {
        return ResponseEntity.ok().body(service.getAll(employeeId, date));
    }

    @Operation(summary = "Удаление данных сертификата сотрудника")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор сертификата") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Аттестация сотрудника удалена.");
    }
}