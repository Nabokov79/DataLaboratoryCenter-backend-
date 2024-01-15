package ru.nabokovsg.templates.dto.columns;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о колонки таблицы")
public class UpdateColumnHeaderTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Порядковый номер колонки")
    @NotNull(message = "sequential cell number should not be null")
    @Positive(message = "sequential cell number must be positive")
    private Integer sequentialNumber;
    @Schema(description = "Заголовок колонки")
    @NotBlank(message = "heading should not be blank")
    private String heading;
}