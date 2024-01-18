package ru.nabokovsg.templates.dto.characteristics;

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
@Schema(description = "Данные для изменения характеристики объекта")
public class UpdateCharacteristicsSurveyObjectDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "sequential section number should not be null")
    @Positive(message = "sequential section number must be positive")
    private Long id;
    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequential section number should not be null")
    @Positive(message = "sequential section number must be positive")
    private String sequentialNumber;
    @Schema(description = "Название характеристики")
    @NotBlank(message = "characteristic name should not be blank")
    private String characteristicName;
    @Schema(description = "Указать порядковый номер в документе")
    @NotNull(message = "sequential number visible should not be null")
    private boolean sequentialNumberVisible;
    @NotNull(message = "useInReport should not be null")
    private boolean useInReport;
    @NotNull(message = "useInProtocol should not be null")
    private boolean useInProtocol;
}