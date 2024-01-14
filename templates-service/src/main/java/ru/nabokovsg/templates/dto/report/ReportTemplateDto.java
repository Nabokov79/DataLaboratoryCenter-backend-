package ru.nabokovsg.templates.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.templates.models.AppendicesTemplate;
import ru.nabokovsg.templates.models.PageTitleTemplate;
import ru.nabokovsg.templates.models.SectionTemplate;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Шаблон отчета")
public class ReportTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Титульный лист")
    private PageTitleTemplate pageTitle;
    @Schema(description = "Подрязделы")
    private List<SectionTemplate> sections;
    @Schema(description = "Приложения")
    private List<AppendicesTemplate> appendices;
}