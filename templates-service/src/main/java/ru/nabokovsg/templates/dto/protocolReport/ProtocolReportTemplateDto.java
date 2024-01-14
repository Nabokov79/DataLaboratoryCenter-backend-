package ru.nabokovsg.templates.dto.protocolReport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.templates.models.ConclusionTemplate;
import ru.nabokovsg.templates.models.SubsectionTemplate;
import ru.nabokovsg.templates.models.TableTemplate;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные шаблона протокола отчета")
public class ProtocolReportTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Заголовок с порядковым номером протокола")
    private String title;
    @Schema(description = "Название протокола")
    private String heading;
    @Schema(description = "Текст пользователя после заголовка")
    private String userTextAfterHeading;
    @Schema(description = "Шаблоны подразделов")
    private List<SubsectionTemplate> subsections;
    @Schema(description = "Шаблон таблиц")
    private List<TableTemplate> tables;
    @Schema(description = "Шаблон заключений")
    private ConclusionTemplate conclusions;
}