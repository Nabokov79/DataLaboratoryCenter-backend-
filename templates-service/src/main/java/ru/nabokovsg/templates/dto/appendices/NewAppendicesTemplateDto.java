package ru.nabokovsg.templates.dto.appendices;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового приложения к документу")
public class NewAppendicesTemplateDto {

    @Schema(description = "Индентификатор отчета или протокола")
    @NotNull(message = "id(report/protocol) should not be null")
    @Positive(message = "id(report/protocol) can only be positive")
    private Long id;
    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequential number should not be null")
    @Positive(message = "sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Название приложения")
    @NotNull(message = "appendices name should not be blank")
    private String appendicesName;

    @Override
    public String toString() {
        return "NewAppendicesTemplateDto{" +
                "id=" + id +
                ", sequentialNumber=" + sequentialNumber +
                ", appendicesName='" + appendicesName + '\'' +
                '}';
    }
}