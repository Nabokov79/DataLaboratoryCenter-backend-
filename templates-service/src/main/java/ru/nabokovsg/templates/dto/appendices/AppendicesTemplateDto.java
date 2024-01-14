package ru.nabokovsg.templates.dto.appendices;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные приложения к документу")
public class AppendicesTemplateDto {

    @Schema(description = "Индентификатор данных приложения")
    private Long id;
    @Schema(description = "Порядковый номер")
    private Integer sequentialNumber;
    @Schema(description = "Название приложения")
    private String appendicesName;
}