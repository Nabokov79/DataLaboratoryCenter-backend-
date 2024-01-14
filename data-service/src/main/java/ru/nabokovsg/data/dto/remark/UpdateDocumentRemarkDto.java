package ru.nabokovsg.data.dto.remark;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения замечания к оформленному сотрудником документу")
public class UpdateDocumentRemarkDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id application should not be blank")
    @Positive(message = "id application must be positive")
    private Long id;
    @Schema(description = "Индентификатор даннных отчетного документа")
    @NotNull(message = "reportingDocumentDataId should not be blank")
    @Positive(message = "reportingDocumentDataId must be positive")
    private Long reportingDocumentDataId;
    @Schema(description = "Текст замечаний к оформлению документа")
    private String documentRemarkText;
    @Schema(description = "Текст замечаний к оформлению чертежа")
    private String drawingRemarkText;
    @Schema(description = "Индентификатор пользователя, оставившего замечание к чертежу")
    private Long employeeId;
    @Schema(description = "Отметка об исправлении замечания")
    private Boolean fixed;
}