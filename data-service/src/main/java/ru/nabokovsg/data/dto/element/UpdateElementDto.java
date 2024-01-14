package ru.nabokovsg.data.dto.element;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.subElement.UpdateSubElementDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации об элементе объекта")
public class UpdateElementDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор типа объекта")
    private Long objectsTypeId;
    @Schema(description = "Название элемента")
    @NotBlank(message = "element name should not be blank")
    private String elementName;
    @Schema(description = "Подэлементы элемента")
    private List<UpdateSubElementDto> subElements;
}