package ru.nabokovsg.templates.dto.clientDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
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