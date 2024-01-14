package ru.nabokovsg.data.dto.objectsType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "раткие данные типа объекта")
public class ShortObjectsTypeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название объекта")
    private String objectName;
    @Schema(description = "Положение объекта")
    private String orientation;
    @Schema(description = "Модель объекта")
    private String model;
}