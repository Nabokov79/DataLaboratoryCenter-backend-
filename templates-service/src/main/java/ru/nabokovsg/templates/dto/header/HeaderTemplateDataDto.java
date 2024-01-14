package ru.nabokovsg.templates.dto.header;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class HeaderTemplateDataDto {

    @Schema(description = "Индентификатор организации")
    private Long organizationId;
    @Schema(description = "Указать в документе полное название организации")
    private Boolean organizationFullName;
    @Schema(description = "Указать в документе адрес организации")
    private Boolean organizationAddress;
    @Schema(description = "Указать лицензию организации")
    private Boolean organizationLicense;
    @Schema(description = "Указать контактные данные организации")
    private Boolean organizationContacts;
    @Schema(description = "Индентификатор филиала организации")
    private Long branchId;
    @Schema(description = "Указать позное название филиала")
    private Boolean branchFullName;
    @Schema(description = "Указать в документе адрес филиала")
    private Boolean branchAddress;
    @Schema(description = "Указать контактные данные филиала")
    private Boolean branchContacts;
    @Schema(description = "Указать лицензию филиала организации")
    private Boolean branchLicense;
    @Schema(description = "Индентификатор подразделения филиала организации")
    private Long departmentId;
    @Schema(description = "Указать полное название подразделения")
    private Boolean departmentFullName;
    @Schema(description = "Указать в документе адрес подразделения филиала")
    private Boolean departmentAddress;
    @Schema(description = "Указать контактные данные подразделения")
    private Boolean departmentContacts;
    @Schema(description = "Указать лицензию подразделения")
    private Boolean departmentLicense;
}