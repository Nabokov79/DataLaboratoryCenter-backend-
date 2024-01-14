package ru.nabokovsg.data.dto.repairMethod;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нового способа ремонта")
public class UpdateRepairMethodDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "place id should not be blank")
    @Positive(message = "place id can only be positive")
    private Long id;
    @Schema(description = "Название способа ремонта")
    @NotBlank(message = "repair method name should not be blank")
    private String methodName;
}