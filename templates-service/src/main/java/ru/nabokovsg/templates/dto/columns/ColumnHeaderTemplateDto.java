package ru.nabokovsg.templates.dto.columns;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные колонки таблицы документа")
public class ColumnHeaderTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Порядковый номер колонки")
    private Integer sequentialNumber;
    @Schema(description = "Заголовок колонки")
    private String heading;
}