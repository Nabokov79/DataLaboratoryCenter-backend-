package ru.nabokovsg.data.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.data.dto.contact.NewContactDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового подразделения филиала")
public class NewDepartmentDto {

    @Schema(description = "Полное наименование района теплоснабжения")
    private String supplyArea;
    @Schema(description = "Краткое наименование района теплоснабжения")
    private String shortSupplyArea;
    @Schema(description = "Полное наименование подразделения")
    @NotBlank(message = "branch should not be blank")
    private String department;
    @Schema(description = "Краткое наименование подразделения")
    private String shortNameDepartment;
    @Schema(description = "Индентификатор адреса")
    private Long addressId;
    @Schema(description = "Контакты")
    private NewContactDto contact;
    @Schema(description = "Индентификатор филиала")
    @NotNull(message = "branch id should not be blank")
    @Positive(message = "branch id must be positive")
    private Long branchId;
}