package ru.nabokovsg.data.dto.rejection.surveyObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные параметров браковки объекта по результатам нивелирования")
public class GeodesicRejectionParametersDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор объекта обследования")
    private Long surveyObjectId;
    @Schema(description = "Для наполненного или пустого объекта")
    private Boolean full;
    @Schema(description = "Максимальная допустимая осадка")
    private Integer maxPrecipitation;
    @Schema(description = "Максимальная допустимая разница между двумя соседними точками измерений")
    private Integer maxDifferenceNeighboringPoints;
    @Schema(description = "Максимальная допустимая разница между двумя диаметральнорасположенными точками измерений")
    private Integer maxDifferenceDiametricPoint;
    @Schema(description = "Допустимое отклонение от нормы")
    private Float measurementError;
}