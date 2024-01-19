package ru.nabokovsg.templates.dto.section;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.templates.dto.passportData.DataOfSurveyObjectTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.models.ProtocolReportTemplate;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные раздела документа")
public class SectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер")
    private Integer sequentialNumber;
    @Schema(description = "Название")
    private String sectionName;
    @Schema(description = "Подразделы")
    private List<SubsectionTemplateDto> subsections;
    @Schema(description = "Протоколы")
    private List<ProtocolReportTemplate> protocols;
    @Schema(description = "Характеристики объекта")
    private List<DataOfSurveyObjectTemplateDto> dataSurveyObject;
}