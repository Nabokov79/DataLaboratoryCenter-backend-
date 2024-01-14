package ru.nabokovsg.data.dto.reportingDocumentData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.application.ShortApplicationDto;
import ru.nabokovsg.data.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.data.models.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Информация о документе результатов проведенного обследования")
public class ReportingDocumentDataDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Информация о заявке")
    private ShortApplicationDto application;
    @Schema(description = "Номер документа")
    private Integer documentNumber;
    @Schema(description = "Время начала работы над документом")
    private LocalDateTime documentStartDate;
    @Schema(description = "Время окончания работы над документом")
    private LocalDateTime documentEndDate;
    @Schema(description = "Дата передачи документа смежному отделу(филиалу)")
    private LocalDate transferDate;
    @Schema(description = "Сотрудник, выполнивший документ")
    private ShortEmployeeDto employeeDocument;
    @Schema(description = "Сотрудник, выполнивший чертеж")
    private ShortEmployeeDto employeeDrawing;
    @Schema(description = "Статус документа")
    private Status documentStatus;
    @Schema(description = "Статус чертежа")
    private Status drawingStatus;
}