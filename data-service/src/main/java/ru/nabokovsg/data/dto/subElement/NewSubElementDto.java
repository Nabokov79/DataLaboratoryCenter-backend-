package ru.nabokovsg.data.dto.subElement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные о подэлементе элемента объекта")
public class NewSubElementDto {

    @Schema(description = "Название подэлемента")
    @NotBlank(message = "sub element name should not be blank")
    private String subElementName;
}