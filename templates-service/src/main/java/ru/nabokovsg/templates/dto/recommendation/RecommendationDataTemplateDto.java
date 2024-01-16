package ru.nabokovsg.templates.dto.recommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
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
@Schema(description = "Данные рекомендации для добавления к отчету или протоколу")
public class RecommendationDataTemplateDto {

    @Schema(description = "Индентификатор отчета или протокола")
    @NotNull(message = "id(section/protocol) should not be null")
    @Positive(message = "id(section/protocol) can only be positive")
    private Long id;
    @Schema(description = "Тип данных, к которым нужно добавить шаблоны рекомендации")
    @NotNull(message = "Data type should not be null")
    private String type;
    @Schema(description = "Индентификаторы отчетов или протоколов/заключений")
    @NotNull(message = "List ids should not be null")
    @NotEmpty(message = "List ids can only be positive")
    private List<Long> ids;
}
