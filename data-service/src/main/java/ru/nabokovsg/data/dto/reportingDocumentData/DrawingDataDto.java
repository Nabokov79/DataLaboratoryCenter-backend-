package ru.nabokovsg.data.dto.reportingDocumentData;

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
public class DrawingDataDto {

    @Schema(description = "Индентификатор данных документа")
    @NotNull(message = "id application should not be null")
    @Positive(message = "id application must be positive")
    private Long id;
    @Schema(description = "Путь к файлу")
    @NotBlank(message = "path to drawing file should not be blank")
    private String path;
    @Schema(description = "Индентификатор сотрудника")
    @NotNull(message = "employee id should not be null")
    @Positive(message = "employee id must be positive")
    private Long employeeId;
}