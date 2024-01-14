package ru.nabokovsg.data.dto.reportingDocument;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.data.models.enums.DocumentType;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные типа документа")
public class ReportingDocumentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String heading;
    @Schema(description = "Тип документа")
    private DocumentType documentType;
}