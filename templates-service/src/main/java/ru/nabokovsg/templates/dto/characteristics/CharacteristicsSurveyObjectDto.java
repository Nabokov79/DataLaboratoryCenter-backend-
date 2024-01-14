package ru.nabokovsg.templates.dto.characteristics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные характеристики объекта")
public class CharacteristicsSurveyObjectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер")
    private String sequentialNumber;
    @Schema(description = "Название характеристики")
    private String characteristicName;
    @Schema(description = "Указать порядковый номер в документе")
    private boolean sequentialNumberVisible;
}