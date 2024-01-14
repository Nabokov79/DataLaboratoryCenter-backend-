package ru.nabokovsg.data.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.data.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.data.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.data.dto.measuringTool.RequestParameters;
import ru.nabokovsg.data.dto.measuringTool.UpdateMeasuringToolDto;
import ru.nabokovsg.data.services.MeasuringToolService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/measuring",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Измерительный инструмент(прибор)",
     description="API для работы с данными измерительных инструментов(приборов)")
public class MeasuringToolController {

    private final MeasuringToolService service;

    @Operation(summary = "Добавление данных нового интструмента(прибора)")
    @PostMapping
    public ResponseEntity<List<MeasuringToolDto>> save(
                              @RequestBody @Valid
                              @Parameter(description = "Инструмент(прибор)") List<NewMeasuringToolDto> measuringTools) {
        return ResponseEntity.ok().body(service.save(measuringTools));
    }

    @Operation(summary = "Изменение данных инструмента(прибора)")
    @PatchMapping
    public ResponseEntity<List<MeasuringToolDto>> update(
                           @RequestBody @Valid
                           @Parameter(description = "Инструмент(прибор)") List<UpdateMeasuringToolDto> measuringTools) {
        return ResponseEntity.ok().body(service.update(measuringTools));
    }

    @Operation(summary = "Получение инструментов(приборов) по заданным параметрам")
    @GetMapping
    public ResponseEntity<List<MeasuringToolDto>> getAll(
     @RequestParam(required = false) @Parameter(description = "Название") String toll,
     @RequestParam(required = false) @Parameter(description = "Модель") String model,
     @RequestParam(required = false) @Parameter(description = "Заводской номер") String workNumber,
     @RequestParam(required = false) @Parameter(description = "Дата изготовления") LocalDate manufacturing,
     @RequestParam(required = false) @Parameter(description = "Дата начала эксплуатации") LocalDate exploitation,
     @RequestParam(required = false)
     @Parameter(description = "Индентификатор завода-изготовителя") Long manufacturerId,
     @RequestParam(required = false)
     @Parameter(description = "Индентификатор метрологической организации") Long organizationId,
     @RequestParam(required = false) @Parameter(description = "Вид контроля") String controlType,
     @RequestParam(required = false) @Parameter(description = "Индентификатор сотрудника") Long employeeId) {
        return ResponseEntity.ok().body(service.getAll(new RequestParameters(toll, model, workNumber, manufacturing,
                                                                           exploitation, manufacturerId, organizationId,
                                                                           controlType, employeeId)));
    }

    @Operation(summary = "Удаление инструмента(прибора)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Null @Positive
                                         @Parameter(description = "Индентификатор инструмента(прибора)") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Инструмент/прибор удален.");
    }
}