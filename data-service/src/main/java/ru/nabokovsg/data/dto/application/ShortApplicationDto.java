package ru.nabokovsg.data.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.data.dto.address.AddressDto;
import ru.nabokovsg.data.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.data.dto.surveyObject.ShortSurveyObjectDto;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class ShortApplicationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Дата проведения обследования/контроля")
    private LocalDate date;
    @Schema(description = "Адрес места проведения обследования/контроля")
    private AddressDto address;
    @Schema(description = "Объект обследования/контроля")
    private ShortSurveyObjectDto surveyObject;
    @Schema(description = "Вид выполненной работы")
    private String workPerformed;
    @Schema(description = "Отчетный документ")
    private ReportingDocumentDto reportingDocument;
    @Schema(description = "Основание для проведения работы по обследованию")
    private String taskSource;
}