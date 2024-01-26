package ru.nabokovsg.data.dto.rejection.surveyObjectElement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации об элементе объекта обследования")
public class UpdateSurveyObjectElementDataDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id must not be null")
    @Positive(message = "id must be negative")
    private Long id;
    @Schema(description = "Индентификатор элемента объекта обследования")
    @NotNull(message = "element id must not be null")
    @Positive(message = "element id must be negative")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента элемента объекта обследования")
    private Long subElementId;
    @Schema(description = "Толщина элемента объекта обследования")
    private Float thickness;
    @Schema(description = "Минимальный диаметр элемента объекта обследования")
    private Integer pipeDiameterMin;
    @Schema(description = "Толщина минимального диаметра элемента объекта обследования")
    private Float pipeWallThicknessMin;
    @Schema(description = "Максимальный диаметр элемента объекта обследования")
    private Integer pipeDiameterMax;
    @Schema(description = "Толщина максимального диаметра элемента объекта обследования")
    private Float pipeWallThicknessMax;
    @Schema(description = "Предельно-минимальное допустимое значение в процентах от номинальной толщины элемента")
    private Float minInPercent;
    @Schema(description = "Предельно-минимальное допустимое значение в миллиметрах")
    private Float min;
    @Schema(description = "допустимое отклонение от нормы")
    @NotNull(message = "measurement error should not be blank")
    @Positive(message = "measurement error can only be positive")
    private Float measurementError;
}