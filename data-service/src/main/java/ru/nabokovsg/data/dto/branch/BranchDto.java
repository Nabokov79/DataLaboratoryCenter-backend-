package ru.nabokovsg.data.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.address.AddressDto;
import ru.nabokovsg.data.dto.contact.ContactDto;
import ru.nabokovsg.data.dto.department.DepartmentDto;
import ru.nabokovsg.data.dto.license.LicenseDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нового филиала")
public class BranchDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String branch;
    @Schema(description = "Краткое название")
    private String shortNameBranch;
    @Schema(description = "Адрес")
    private AddressDto address;
    @Schema(description = "Лицензия, аттестация")
    private List<LicenseDto> licenses;
    @Schema(description = "Подразделения")
    private List<DepartmentDto> departments;
    @Schema(description = "Контакты")
    private ContactDto contact;
}