package ru.nabokovsg.data.dto.element;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Краткие данные элемента объекта")
public class ShortElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название элемента")
    private String elementName;
}