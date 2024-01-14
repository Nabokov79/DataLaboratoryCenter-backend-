package ru.nabokovsg.templates.dto.subsectionDada;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные подраздела")
public class SubsectionDataTemplateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Данные подраздела")
    private String value;
}