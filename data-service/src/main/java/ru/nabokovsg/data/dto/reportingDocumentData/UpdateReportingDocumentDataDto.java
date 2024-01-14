package ru.nabokovsg.data.dto.reportingDocumentData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.models.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для внесения информации о документе объекта обследования")
public class  UpdateReportingDocumentDataDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Время начала работы над документом")
    private LocalDateTime documentStartDate;
    @Schema(description = "Время окончания работы над документом")
    private LocalDateTime documentEndDate;
    @Schema(description = "Дата передачи документа смежному отделу(филиалу)")
    private LocalDate transferDate;
    @Schema(description = "Индентификатор сотрудника, выполнившего документ")
    private Long employeeDocumentId;
    @Schema(description = "Индентификатор сотрудника, выполнившего чертеж")
    private Long employeeDrawingId;
    @Schema(description = "Статус документа")
    private Status documentStatus;
    @Schema(description = "Статус чертежа")
    private Status drawingStatus;
}