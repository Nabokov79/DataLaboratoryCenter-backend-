package ru.nabokovsg.data.dto.documentation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные нового нормативно-технического документа")
public class NewDocumentationDto {

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