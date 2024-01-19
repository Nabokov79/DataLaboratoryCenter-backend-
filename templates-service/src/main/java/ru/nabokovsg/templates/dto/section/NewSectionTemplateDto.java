package ru.nabokovsg.templates.dto.section;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового раздела документа")
public class NewSectionTemplateDto {

    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequential section number should not be null")
    @Positive(message = "sequential section number must be positive")
    private Integer sequentialNumber;
    @Schema(description = "Название")
    @NotBlank(message = "section name should not be blank")
    private String sectionName;
    @Schema(description = "Указать характеристи объекта обследования")
    @NotNull(message = "characteristics should not be null")
    private boolean characteristic;
}