package ru.nabokovsg.data.dto.certificate;

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
@Schema(description = "Данные для изменения информации о сертификате сотрудника")
public class UpdateCertificateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id must be positive")
    private long id;
    @Schema(description = "Тип документа")
    @NotBlank(message = "document type should not be blank")
    private String documentType;
    @Schema(description = "Номер сертификата")
    @NotBlank(message = "number should not be blank")
    private String certificateNumber;
    @Schema(description = "Вид контроля соглано сертификата")
    @NotBlank(message = "type should not be blank")
    private String controlType;
    @Schema(description = "Квалификационный уровень сотрудника по данным из сертификата")
    @NotNull(message = "level should not be blank")
    @Positive(message = "level must be positive")
    private String level;
    @Schema(description = "Дата выдачи сертификата специализированной организацией")
    @NotNull(message = "start date should not be blank")
    private LocalDate startDate;
    @Schema(description = "Дата окончания действия сертификата")
    @NotNull(message = "end date not be blank")
    private LocalDate endDate;
    @Schema(description = "Шифр объектов, для контроля которых допущен сотрудник согласно данным сертификата")
    @NotBlank(message = "points should not be blank")
    private String points;
    @Schema(description = "Индентификатор организации, выдавшей сертификат")
    @NotNull(message = "organization should not be blank")
    @Positive(message = "organization id must be positive")
    private Long organizationId;
    @Schema(description = "Индентификатор сотрудника, которому принадлежит сертификат")
    @NotNull(message = "user id should not be blank")
    @Positive(message = "user id must be positive")
    private Long employeeId;
}