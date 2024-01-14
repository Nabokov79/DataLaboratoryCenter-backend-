package ru.nabokovsg.data.dto.reportingDocument;

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
@Schema(description = "Данные для изменения информации о типе документа")
public class UpdateReportingDocumentDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id reporting document should not be blank")
    @Positive(message = "id reporting document must be positive")
    private Long id;
    @Schema(description = "Название документа")
    @NotBlank(message = "title should not be blank")
    private String title;
    @Schema(description = "Заголовок документа")
    @NotBlank(message = "heading should not be blank")
    private String heading;
}