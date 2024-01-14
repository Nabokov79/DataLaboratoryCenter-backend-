package ru.nabokovsg.data.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.branch.UltraShortBranchDto;
import ru.nabokovsg.data.dto.certificate.CertificateDto;
import ru.nabokovsg.data.dto.contact.ContactDto;
import ru.nabokovsg.data.dto.department.ShortDepartmentDto;
import ru.nabokovsg.data.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.data.dto.organization.UltraShortOrganizationDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные о сотруднике")
public class EmployeeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Отчество")
    private String patronymic;
    @Schema(description = "Фамилия")
    private String surname;
    @Schema(description = "Должность")
    private String post;
    @Schema(description = "Контакты сотрудника")
    private ContactDto contact;
    @Schema(description = "Организация")
    private UltraShortOrganizationDto organization;
    @Schema(description = "Филиал организации")
    private UltraShortBranchDto branch;
    @Schema(description = "Подразделение филиала организации")
    private ShortDepartmentDto department;
    @Schema(description = "Список сертификатов сотрудника")
    private List<CertificateDto> certificate;
    @Schema(description = "Список средств(приборов) закрепленных за сотрудником")
    private List<MeasuringToolDto> measuringTool;
}