package ru.nabokovsg.data.dto.license;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные новой лицензии")
public class NewLicenseDto {

    @Schema(description = "Индентификатор структурного подразделения организации")
    @NotNull(message = " division id should not be null")
    @Positive(message = "division id can only be positive")
    private Long divisionId;
    @Schema(description = "Вид документа")
    @NotBlank(message = "document should not be blank")
    private String documentType;
    @Schema(description = "Номер свидетельства")
    @NotBlank(message = "license number should not be blank")
    private String licenseNumber;
    @Schema(description = "Дата выдачи лицензии")
    @NotNull(message = "start date license should not be null")
    private LocalDate startData;
    @Schema(description = "Дата окончания действия свидетельства")
    @NotNull(message = "end date license should not be null")
    private LocalDate endData;
    @Schema(description = "Индентификатор организации выдавшей лицензию")
    @NotNull(message = "issuedLicense id should not be null")
    private Long issuedLicenseId;
    @Schema(description = "Принадлежность лицензии")
    @NotNull(message = "license should not be null")
    private String divisionType;
}