package ru.nabokovsg.data.dto.surveyObjectElementData.rejection;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения параметров браковки элемента объекта")
public class UpdateElementRejectionParametersDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Предельно-минимальное допустимое значение в миллиметрах")
    private Float min;
    @Schema(description = "Предельно-минимальное допустимое значение в процентах")
    private Float minInPercent;
    @Schema(description = "Минимальная допустимая твердость металла элемента")
    private Integer minHardness;
    @Schema(description = "Мфксимальная допустимая твердость металла элемента")
    private Integer maxHardness;
    @Schema(description = "допустимое отклонение от нормы")
    @NotNull(message = "measurement error should not be blank")
    @Positive(message = "measurement error can only be positive")
    private Float measurementError;
}