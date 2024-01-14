package ru.nabokovsg.data.dto.sizeParameters;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о параметры дефекте")
public class UpdateSizeParametersDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "drawing id should not be blank")
    @Positive(message = "drawing id can only be positive")
    private Long id;
    @Schema(description = "Название параметра дефекта")
    @NotBlank(message = "parameters name defect name should not be blank")
    private String parametersName;
    @Schema(description = "Единица измерения параметра дефекта")
    @NotBlank(message = "parameters name defect name should not be blank")
    private String unitMeasurement;
}