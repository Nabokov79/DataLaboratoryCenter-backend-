package ru.nabokovsg.data.dto.license;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения иформации о лицензии")
public class UpdateLicenseDto {

    @Schema(description = "Индентификатор лицензии")
    @NotNull(message = "id should not be blank")
    @Positive(message = "license id can only be positive")
    private Long id;
    @Schema(description = "Вид документа")
    @NotBlank(message = "document should not be blank")
    private String documentType;
    @Schema(description = "Номер свидетельства")
    @NotBlank(message = "license number should not be blank")
    private String licenseNumber;
    @Schema(description = "Дата выдачи лицензии")
    @NotNull(message = "start date license should not be blank")
    private LocalDate startData;
    @Schema(description = "Дата окончания действия свидетельства")
    @NotNull(message = "end date license should not be blank")
    private LocalDate endData;
    @Schema(description = "Индентификатор организации выдавшей лицензию")
    @NotNull(message = "issuedLicense id should not be blank")
    private Long issuedLicenseId;
}