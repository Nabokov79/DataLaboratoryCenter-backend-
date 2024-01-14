package ru.nabokovsg.data.dto.defect;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.sizeParameters.UpdateSizeParametersDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о дефекте")
public class UpdateDefectDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "drawing id should not be blank")
    @Positive(message = "drawing id can only be positive")
    private Long id;
    @Schema(description = "Название дефекта")
    @NotBlank(message = "defect name should not be blank")
    private String defectName;
    @Schema(description = "Параметры дефекта")
    @NotNull(message = "parameters list should not be null")
    @NotEmpty(message = "parameters list can only be empty")
    private List<UpdateSizeParametersDto> parameters;
}