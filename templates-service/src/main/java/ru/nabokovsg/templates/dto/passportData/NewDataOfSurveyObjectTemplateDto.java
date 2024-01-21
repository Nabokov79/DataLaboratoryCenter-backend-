package ru.nabokovsg.templates.dto.passportData;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные новой характеристики объекта")
public class NewDataOfSurveyObjectTemplateDto {

    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    @Positive(message = "object type id must be positive")
    private Long objectTypeId;
    @Schema(description = "Указать порядковый номер в документе")
    @NotNull(message = "useInReport should not be null")
    private boolean useInReport;
    @Schema(description = "Указать порядковый номер в документе")
    @NotNull(message = "useInProtocolSurvey should not be null")
    private boolean useInProtocolSurvey;
    @Schema(description = "Указать порядковый номер в документе")
    @NotNull(message = "useInProtocolQuality should not be null")
    private boolean useInProtocolQuality;
    @Schema(description = "Новый шаблон данных паспорта объекта")
    private List<@Valid NewPassportDataTemplateDto> passportData;
}