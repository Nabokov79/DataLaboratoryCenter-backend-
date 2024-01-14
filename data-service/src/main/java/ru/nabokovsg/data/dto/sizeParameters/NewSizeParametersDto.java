package ru.nabokovsg.data.dto.sizeParameters;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные параметра дефекта")
public class NewSizeParametersDto {

    @Schema(description = "Название параметра дефекта")
    @NotBlank(message = "parameters name defect name should not be blank")
    private String parametersName;
    @Schema(description = "Единица измерения параметра дефекта")
    @NotBlank(message = "parameters name defect name should not be blank")
    private String unitMeasurement;

    @Override
    public String toString() {
        return "NewSizeParametersDto{" +
                "parametersName='" + parametersName + '\'' +
                ", unitMeasurement='" + unitMeasurement + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewSizeParametersDto that = (NewSizeParametersDto) o;
        return Objects.equals(parametersName, that.parametersName)
                && Objects.equals(unitMeasurement, that.unitMeasurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parametersName, unitMeasurement);
    }
}