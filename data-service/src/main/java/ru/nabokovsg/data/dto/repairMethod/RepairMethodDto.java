package ru.nabokovsg.data.dto.repairMethod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные способа ремонта")
public class RepairMethodDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название способа ремонта")
    private String methodName;
}