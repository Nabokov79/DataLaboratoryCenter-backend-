package ru.nabokovsg.templates.dto.protocol;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.templates.dto.header.NewHeaderTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового протокола/заключения")
public class UpdateProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    @Positive(message = "object type id can only be positive")
    private Long objectTypeId;
    @Schema(description = "Индентификатор типа отчетного документа")
    @NotNull(message = "reporting document id should not be null")
    @Positive(message = "reporting document id can only be positive")
    private Long reportingDocumentId;
    @Schema(description = "Данные заголовка")
    @NotNull(message = "header not be null")
    private NewHeaderTemplateDto header;
    @Schema(description = "Сохранить как протокол результата обследования")
    @NotNull(message = "isProtocolSurvey should not be null")
    private boolean isProtocolSurvey;
    @Schema(description = "Сохранить как протокол контроля качества")
    @NotNull(message = "isProtocolQuality should not be null")
    private boolean isProtocolQuality;
}