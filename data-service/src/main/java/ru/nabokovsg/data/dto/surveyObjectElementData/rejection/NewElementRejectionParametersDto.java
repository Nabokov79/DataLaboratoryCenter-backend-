package ru.nabokovsg.data.dto.surveyObjectElementData.rejection;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Новые параметры браковки элемента объекта")
public class NewElementRejectionParametersDto {

    @Schema(description = "Индентификатор элемента")
    @NotNull(message = "element id should not be blank")
    @Positive(message = "element id can only be positive")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента")
    private Long subElementId;
    @Schema(description = "Предельно-минимальное допустимое значение в миллиметрах")
    private Float min;
    @Schema(description = "Предельно-минимальное допустимое значение в процентах")
    private Float minInPercent;
    @Schema(description = "Минимальная допустимая твердость металла элемента")
    private Integer minHardness;
    @Schema(description = "Максимальная допустимая твердость металла элемента")
    private Integer maxHardness;
    @Schema(description = "допустимое отклонение от нормы")
    @NotNull(message = "measurement error should not be blank")
    @Positive(message = "measurement error can only be positive")
    private Float measurementError;
}
