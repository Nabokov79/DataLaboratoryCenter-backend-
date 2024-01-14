package ru.nabokovsg.data.dto.element;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.subElement.SubElementDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные элемента объекта")
public class ElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название элемента")
    private String elementName;
    @Schema(description = "Подэлементы элемента")
    private List<SubElementDto> subElements;
}