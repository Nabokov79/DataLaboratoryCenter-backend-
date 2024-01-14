package ru.nabokovsg.templates.dto.subsectionDada;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового подраздела")
public class DivisionDataDto {

    @Schema(description = "Тип структурного подразделения")
    @NotBlank(message = "division type should not be blank")
    private String divisionType;
    @Schema(description = "Индентификатор структурного подразделения организации")
    @NotNull(message = "division id should not be null")
    @Positive(message = "division id can only be positive")
    private Long divisionId;
    @Schema(description = "Пользовательское название структурного подразделения организации")
    private String divisionName;
    @Schema(description = "Указать адресс структурного подразделения")
    @NotNull(message = "division address should not be null")
    private Boolean address;
    @Schema(description = "Указать лицензию/аттестацию структурного подразделения")
    @NotNull(message = "division license should not be null")
    private Boolean license;

    @Override
    public String toString() {
        return "DivisionDataDto{" +
                "divisionType='" + divisionType + '\'' +
                ", divisionId=" + divisionId +
                ", divisionName='" + divisionName + '\'' +
                ", address=" + address +
                ", license=" + license +
                '}';
    }
}