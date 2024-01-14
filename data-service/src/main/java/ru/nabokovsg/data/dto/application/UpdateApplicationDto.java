package ru.nabokovsg.data.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о заявки")
public class UpdateApplicationDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id application should not be blank")
    @Positive(message = "id application must be positive")
    private Long id;
    @Schema(description = "Индентификатор организации")
    @NotNull(message = "organization id should not be blank")
    @Positive(message = "organization id can only be positive")
    private Long organizationId;
    @Schema(description = "Индентификатор филиала организации")
    @NotNull(message = "branch id should not be blank")
    @Positive(message = "branch id can only be positive")
    private Long branchId;
    @Schema(description = "Индентификатор подразделения филиала организации")
    @NotNull(message = "department id should not be blank")
    @Positive(message = "department id can only be positive")
    private Long departmentId;
    @Schema(description = "Дата проведения обследования/контроля")
    private LocalDate date;
    @Schema(description = "Индентификатор адреса места проведения обследования/контроля")
    @NotNull(message = "address id should not be blank")
    @Positive(message = "address id can only be positive")
    private Long addressId;
    @Schema(description = "Индентификатор объекта обследования/контроля")
    @NotNull(message = "object id should not be blank")
    @Positive(message = "object id can only be positive")
    private Long surveyObjectId;
    @Schema(description = "Вид выполненной работы")
    @NotBlank(message = "work performed id should not be blank")
    private String workPerformed;
    @Schema(description = "Индентификатор отчетного документа")
    @NotNull(message = "work performed id should not be blank")
    @Positive(message = "work performed id can only be positive")
    private Long reportingDocumentId;
    @Schema(description = "Список индентификаторов сотрудников, проводивших обследование/контроль объекта")
    private List<Long> employeesIds;
    @Schema(description = "Основание для проведения работы по обследованию")
    @NotBlank(message = "task source should not be blank")
    private String taskSource;
    @Schema(description = "Необходимость выполнения чертежа")
    @NotNull(message = "task source should not be blank")
    private Boolean needDrawing;
    @Schema(description = "Комментари")
    private String comment;
}