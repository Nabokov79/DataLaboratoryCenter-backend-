package ru.nabokovsg.data.dto.license;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.organization.ShortOrganizationDto;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные лицензии")
public class LicenseDto {

    @Schema(description = "Индентификатор лицензии")
    private Long id;
    @Schema(description = "Вид документа")
    private String documentType;
    @Schema(description = "Номер свидетельства")
    private String licenseNumber;
    @Schema(description = "Дата выдачи лицензии")
    private LocalDate startData;
    @Schema(description = "Дата окончания действия свидетельства")
    private LocalDate endData;
    @Schema(description = "Организация выдавшая лицензию")
    private ShortOrganizationDto issuedLicense;
}