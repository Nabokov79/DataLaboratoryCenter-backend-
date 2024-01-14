package ru.nabokovsg.templates.dto.pageTitle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.templates.models.HeaderTemplate;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Краткие данные титульного листа")
public class ShortPageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Заголовок")
    private HeaderTemplate header;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String heading;
    @Schema(description = "Строка наименования объекта")
    private String object;
    @Schema(description = "Строка местоположения")
    private String installationLocation;
}