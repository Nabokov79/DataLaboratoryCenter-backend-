package ru.nabokovsg.templates.dto.clientDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные подэлемента элемента объекта")
public class SubElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название подэлемента")
    private String subElementName;
}