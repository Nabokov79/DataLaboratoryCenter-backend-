package ru.nabokovsg.data.dto.surveyObject;

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
@Schema(description = "Данные нового объекта")
public class NewSurveyObjectDto {

    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "id object type must not be null")
    @Positive(message = "id object type must be negative")
    private Long objectTypeId;
    @Schema(description = "Стационарный номер объекта")
    private Integer stationaryNumber;
    @Schema(description = "Объем объекта")
    private Integer volume;
    @Schema(description = "Индентификатор строения")
    @NotNull(message = "id building address must not be null")
    @Positive(message = "id building address must be negative")
    private Long buildingId;
}