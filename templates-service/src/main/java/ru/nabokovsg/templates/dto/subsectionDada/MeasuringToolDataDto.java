package ru.nabokovsg.templates.dto.subsectionDada;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для изменения информации в подразделе")
public class MeasuringToolDataDto {

    @Schema(description = "Название")
    @NotBlank(message = "toll should not be blank")
    private String value;

    @Override
    public String toString() {
        return "MeasuringToolDataDto{" +
                "value='" + value + '\'' +
                '}';
    }
}