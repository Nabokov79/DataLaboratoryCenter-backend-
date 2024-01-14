package ru.nabokovsg.data.dto.surveyObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.building.BuildingDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.SurveyObjectElementDataDto;
import ru.nabokovsg.data.dto.objectsType.ShortObjectsTypeDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные объекта")
public class SurveyObjectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип объекта")
    private ShortObjectsTypeDto objectType;
    @Schema(description = "Стационарный номер объекта")
    private Integer stationaryNumber;
    @Schema(description = "Объем объекта")
    private Integer volume;
    @Schema(description = "Данные элементов объекта обследования")
    private List<SurveyObjectElementDataDto> elements;
    @Schema(description = "Строение")
    private BuildingDto building;
}