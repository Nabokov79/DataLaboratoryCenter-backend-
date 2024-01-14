package ru.nabokovsg.templates.dto.protocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDto;
import ru.nabokovsg.templates.models.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные протокола/заключения")
public class ProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Данные типа объекта")
    private String objectsType;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String heading;
    @Schema(description = "Заголовок")
    private HeaderTemplateDto header;
    @Schema(description = "Подразделы")
    private List<SubsectionTemplate> subsections;
    @Schema(description = "Таблицы")
    private List<TableTemplate> tables;
    @Schema(description = "Заключение по результатм")
    private ConclusionTemplate conclusions;
    @Schema(description = "Рекомендации")
    private List<RecommendationTemplate> recommendations;
    @Schema(description = "Приложения")
    private List<AppendicesTemplate> appendices;
}