package ru.nabokovsg.templates.dto.recommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные рекомендации")
public class RecommendationTemplateDto {

    @Schema(description = "Индентификатор")
    Long id;
    @Schema(description = "Индентификатор типа объекта")
    Long objectTypeId;
    @Schema(description = "Текст рекомендации")
    String recommendationText;
}