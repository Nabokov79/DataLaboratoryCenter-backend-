package ru.nabokovsg.data.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Основные сведения о филиале организации")
public class UltraShortBranchDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String branch;
    @Schema(description = "Краткое название")
    private String shortNameBranch;
}