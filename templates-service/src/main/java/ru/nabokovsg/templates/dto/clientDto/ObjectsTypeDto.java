package ru.nabokovsg.templates.dto.clientDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные типа объекта")
public class ObjectsTypeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название объекта")
    private String objectName;
    @Schema(description = "Модель объекта")
    private String model;
    @Schema(description = "Положение объекта")
    private String orientation;
    @Schema(description = "Элементы объекта")
    private List<ElementDto> elements;
    @Schema(description = "Нормативно-техничсекая документация на объект")
    private List<DocumentationDto> documentations;
    @Schema(description = "Дефекты объекта")
    private List<DefectDto> defects;
    @Schema(description = "Способы ремонта объекта")
    private List<RepairMethodDto> repairMethods;
}