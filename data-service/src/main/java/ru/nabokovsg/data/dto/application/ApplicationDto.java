package ru.nabokovsg.data.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.address.AddressDto;
import ru.nabokovsg.data.dto.branch.ShortBranchDto;
import ru.nabokovsg.data.dto.department.ShortDepartmentDto;
import ru.nabokovsg.data.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.data.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.data.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.data.dto.surveyObject.ShortSurveyObjectDto;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Полные данные о выполненной работе")
public class ApplicationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Организация")
    private ShortOrganizationDto organization;
    @Schema(description = "Филиал организации")
    private ShortBranchDto branch;
    @Schema(description = "Подразделение филиала организации")
    private ShortDepartmentDto department;
    @Schema(description = "Дата проведения обследования/контроля")
    private LocalDate date;
    @Schema(description = "Адрес места проведения обследования/контроля")
    private AddressDto address;
    @Schema(description = "Объект обследования/контроля")
    private ShortSurveyObjectDto surveyObject;
    @Schema(description = "Вид выполненной работы")
    private String workPerformed;
    @Schema(description = "Отчетный документ")
    private ReportingDocumentDto reportingDocument;
    @Schema(description = "Список индентификаторов сотрудников, проводивших обследование/контроль объекта")
    private List<ShortEmployeeDto> employees;
    @Schema(description = "Основание для проведения работы по обследованию")
    private String taskSource;
    @Schema(description = "Необходимость выполнения чертежа")
    private Boolean needDrawing;
    @Schema(description = "Комментари")
    private String comment;
}