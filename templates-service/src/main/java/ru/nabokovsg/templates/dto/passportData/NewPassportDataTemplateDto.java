package ru.nabokovsg.templates.dto.passportData;

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
@Schema(description = "Новый шаблон данных паспорта объекта")
public class NewPassportDataTemplateDto {

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
    @Schema(description = "Указать местоположение объекта")
    @NotNull(message = "location should not be null")
    private boolean location;
}