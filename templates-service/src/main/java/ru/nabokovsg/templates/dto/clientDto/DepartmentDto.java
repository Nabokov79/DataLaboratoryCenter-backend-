package ru.nabokovsg.templates.dto.clientDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные подразделения филиала")
public class DepartmentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное наименование района теплоснабжения")
    private String supplyArea;
    @Schema(description = "Краткое наименование района теплоснабжения")
    private String shortSupplyArea;
    @Schema(description = "Полное название")
    private String department;
    @Schema(description = "Краткое название")
    private String shortNameDepartment;
    @Schema(description = "Адрес")
    private AddressDto address;
    @Schema(description = "Данные о производственном здании")
    private List<BuildingDto> buildings;
    @Schema(description = "Лицензия, аттестация")
    private List<LicenseDto> licenses;
    @Schema(description = "Контакты")
    private ContactDto contact;
}