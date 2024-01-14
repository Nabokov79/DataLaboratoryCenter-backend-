package ru.nabokovsg.data.dto.documentation;

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
@Schema(description = "Данные для изменения информации о нормативно-техническом документе")
public class UpdateDocumentationDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id author should not be blank")
    @Positive(message = "id author must be positive")
    private Long id;
    @Schema(description = "Вид документа")
    private String view;
    @Schema(description = "Номер документа")
    private String number;
    @Schema(description = "Заголовок документа")
    @NotBlank(message = "title should not be blank")
    private String title;
    @Schema(description = "Методический документ")
    @NotNull(message = "methodologicalDocument should not be null")
    private Boolean methodologicalDocument;
    @Schema(description = "Нормативный документа")
    @NotNull(message = "regulatoryDocument should not be null")
    private Boolean regulatoryDocument;
}