package ru.nabokovsg.templates.dto.passportData;

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
@Schema(description = "Данные для изменения информации в шаблоне данных паспорта объекта")
public class UpdatePassportDataTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "sequential section number should not be null")
    @Positive(message = "sequential section number must be positive")
    private Long id;
    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequential section number should not be null")
    @Positive(message = "sequential section number must be positive")
    private Integer sequentialNumber;
    @Schema(description = "Название характеристики")
    @NotBlank(message = "characteristic name should not be blank")
    private String characteristicName;
    @Schema(description = "Указать порядковый номер в документе")
    @NotNull(message = "sequential number visible should not be null")
    private boolean sequentialNumberVisible;
}