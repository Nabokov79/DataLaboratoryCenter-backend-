package ru.nabokovsg.templates.dto.autoData;

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
@Schema(description = "Данные для автоматического сбора информации")
public class AutoDataCollectionDto {

    @Schema(description = "Индентификатор протокола/заключения, протокола в составе отчета")
    @NotNull(message = "protocol id should not be null")
    @Positive(message = "protocol id can only be positive")
    private Long protocolId;
    @Schema(description = "Добавить заключение из протокола")
    @NotNull(message = "protocolConclusion should not be null")
    private boolean protocolConclusion;
    @Schema(description = "Составить сводную таблицу по данным таблицы протокола")
    @NotNull(message = "autoTable should not be null")
    private boolean autoTable;

    @Override
    public String toString() {
        return "AutoDataCollectionDto{" +
                "protocolId=" + protocolId +
                ", protocolConclusion=" + protocolConclusion +
                ", autoTable=" + autoTable +
                '}';
    }
}