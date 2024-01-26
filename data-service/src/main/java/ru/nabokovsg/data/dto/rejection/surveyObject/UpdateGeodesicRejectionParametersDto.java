package ru.nabokovsg.data.dto.rejection.surveyObject;

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
@Schema(description = "Данные для изменения параметров браковки объекта по результатам нивелирования")
public class UpdateGeodesicRejectionParametersDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор объекта обследования")
    @NotNull(message = "surveyObject id should not be blank")
    @Positive(message = "surveyObject id can only be positive")
    private Long surveyObjectId;
    @Schema(description = "Для наполненного или пустого объекта")
    @NotNull(message = "full should not be null")
    private Boolean full;
    @Schema(description = "Максимальная допустимая осадка")
    private Integer maxPrecipitation;
    @Schema(description = "Максимальная допустимая разница между двумя соседними точками измерений")
    @NotNull(message = "maxDifferenceNeighboringPoints should not be blank")
    @Positive(message = "maxDifferenceNeighboringPoints can only be positive")
    private Integer maxDifferenceNeighboringPoints;
    @Schema(description = "Максимальная допустимая разница между двумя диаметральнорасположенными точками измерений")
    @NotNull(message = "maxDifferenceDiametricPoint should not be blank")
    @Positive(message = "maxDifferenceDiametricPoint can only be positive")
    private Integer maxDifferenceDiametricPoint;
    @Schema(description = "Допустимое отклонение от нормы")
    @NotNull(message = "measurementError should not be blank")
    @Positive(message = "measurementError can only be positive")
    private Float measurementError;
}