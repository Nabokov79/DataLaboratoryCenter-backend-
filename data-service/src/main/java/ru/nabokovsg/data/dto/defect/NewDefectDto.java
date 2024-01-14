package ru.nabokovsg.data.dto.defect;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.sizeParameters.NewSizeParametersDto;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о дефекте")
public class NewDefectDto {

    @Schema(description = "Название дефекта")
    @NotBlank(message = "defect name should not be blank")
    private String defectName;
    @Schema(description = "Параметры дефекта")
    @NotNull(message = "parameters list should not be null")
    @NotEmpty(message = "parameters list can only be empty")
    private List<NewSizeParametersDto> parameters;

    @Override
    public String toString() {
        return "NewDefectDto{" +
                "defectName='" + defectName + '\'' +
                ", parameters=" + parameters +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewDefectDto that = (NewDefectDto) o;
        return Objects.equals(defectName, that.defectName) && Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(defectName, parameters);
    }
}