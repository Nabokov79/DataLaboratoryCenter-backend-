package ru.nabokovsg.templates.dto.passportData;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения характеристики объекта")
public class UpdateDataOfSurveyObjectTemplateDto {

    @Schema(description = "Указать порядковый номер в документе")
    @NotNull(message = "useInReport should not be null")
    private boolean useInReport;
    @Schema(description = "Указать порядковый номер в документе")
    @NotNull(message = "useInProtocolSurvey should not be null")
    private boolean useInProtocolSurvey;
    @Schema(description = "Указать порядковый номер в документе")
    @NotNull(message = "useInProtocolQuality should not be null")
    private boolean useInProtocolQuality;
    @Schema(description = "Указать местоположение объекта")
    @NotNull(message = "location should not be null")
    private boolean location;
    @Schema(description = "Данные для изменения информации в шаблоне данных паспорта объекта")
    private List<@Valid UpdatePassportDataTemplateDto> passportData;
}