package ru.nabokovsg.data.dto.surveyObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.objectsType.ShortObjectsTypeDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные объекта")
public class ShortSurveyObjectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип объекта")
    private ShortObjectsTypeDto objectType;
    @Schema(description = "Стационарный номер")
    private Integer stationaryNumber;
    @Schema(description = "Объем объекта")
    private Integer volume;
}