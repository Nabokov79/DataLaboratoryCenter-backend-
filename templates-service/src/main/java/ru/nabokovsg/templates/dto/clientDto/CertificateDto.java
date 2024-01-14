package ru.nabokovsg.templates.dto.clientDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Возвращаемый сертификат аттестации сотрудника.")
public class CertificateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Тип документа")
    private String documentType;
    @Schema(description = "Номер сертификата")
    private String certificateNumber;
    @Schema(description = "Вид контроля соглано сертификата")
    private String controlType;
    @Schema(description = "Квалификационный уровень сотрудника по данным из сертификата")
    private String level;
    @Schema(description = "Дата выдачи сертификата специализированной организацией")
    private LocalDate startDate;
    @Schema(description = "Дата окончания действия сертификата")
    private LocalDate endDate;
    @Schema(description = "Шифр объектов, для контроля которых допущен сотрудник согласно данным сертификата")
    private String points;
    @Schema(description = "Организация, выдавшая сертификат")
    private ShortOrganizationDto organization;
}