package ru.nabokovsg.templates.dto.protocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.templates.dto.header.HeaderTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нового протокола/заключения")
public class ShortProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Данные типа объекта")
    private String objectType;
    @Schema(description = "Заголовок")
    private HeaderTemplateDto header;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String heading;
}