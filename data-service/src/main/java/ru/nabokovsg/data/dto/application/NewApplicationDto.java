package ru.nabokovsg.data.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные новой заявки")
public class NewApplicationDto {

    @Schema(description = "Индентификатор организации")
    private Long organizationId;
    @Schema(description = "Индентификатор филиала организации")
    private Long branchId;
    @Schema(description = "Индентификатор подразделения филиала организации")
    private Long departmentId;
    @Schema(description = "Дата проведения обследования/контроля")
    private LocalDate date;
    @Schema(description = "Индентификатор адреса места проведения обследования/контроля")
    private Long addressId;
    @Schema(description = "Индентификатор объекта обследования/контроля")
    private Long surveyObjectId;
    @Schema(description = "Вид выполненной работы")
    private String workPerformed;
    @Schema(description = "Индентификатор отчетного документа")
    private Long reportingDocumentId;
    @Schema(description = "Список индентификаторов сотрудников, проводивших обследование/контроль объекта")
    private List<Long> employeesIds;
    @Schema(description = "Основание для проведения работы по обследованию")
    private String taskSource;
    @Schema(description = "Необходимость выполнения чертежа")
    private Boolean needDrawing;
    @Schema(description = "Комментарий к заявке")
    private String comment;
}