package ru.nabokovsg.data.dto.element;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.subElement.NewSubElementDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нового элемента объекта")
public class NewElementDto {

    @Schema(description = "Название элемента")
    @NotBlank(message = "element name should not be blank")
    private String elementName;
    @Schema(description = "Подэлементы элемента")
    private List<NewSubElementDto> subElements;
}