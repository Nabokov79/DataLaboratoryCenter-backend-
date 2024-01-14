package ru.nabokovsg.data.dto.remark;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.data.dto.reportingDocumentData.ReportingDocumentDataDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные замечания к оформленному сотрудником документу")
public class DocumentRemarkDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Данные для изменения информации о заявки")
    private ReportingDocumentDataDto reportingDocumentData;
    @Schema(description = "Текст замечаний к оформлению документа")
    private String documentRemarkText;
    @Schema(description = "Пользователь, оставивший замечание")
    private ShortEmployeeDto employeeDocument;
    @Schema(description = "Текст замечаний к оформлению чертежа")
    private String drawingRemarkText;
    @Schema(description = "Пользователь, оставивший замечание к чертежу")
    private ShortEmployeeDto employeeDrawing;
}