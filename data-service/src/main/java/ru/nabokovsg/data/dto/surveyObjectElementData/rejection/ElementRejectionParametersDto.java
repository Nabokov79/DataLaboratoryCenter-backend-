package ru.nabokovsg.data.dto.surveyObjectElementData.rejection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Параметры браковки элемента объекта")
public class ElementRejectionParametersDto {

    @Schema(description = "Индентификатор")
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
    private Float measurementError;
}