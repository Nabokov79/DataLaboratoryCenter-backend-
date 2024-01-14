package ru.nabokovsg.templates.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.templates.dto.autoData.AutoDataCollectionDto;
import ru.nabokovsg.templates.dto.subsectionDada.SubsectionDataTemplateDto;
import ru.nabokovsg.templates.dto.table.TableTemplateDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные подраздела")
public class SubsectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер подраздела")
    private double sequentialNumber;
    @Schema(description = "Название подраздела")
    private String subsectionName;
    @Schema(description = "Текст пользователя")
    private String userText;
    @Schema(description = "Показать номер подраздела в документе")
    private boolean sequentialNumberVisible;
    @Schema(description = "Данные структурного подразделения")
    private String divisionData;
    @Schema(description = "Данные об аттестации сотрудника")
    private String certificateEmployee;
    @Schema(description = "Данные нормативно-технической документации")
    private List<SubsectionDataTemplateDto> documentation;
    @Schema(description = "Данные шаблонов средств измерения и контроля")
    private List<SubsectionDataTemplateDto> measuringTools;
    @Schema(description = "Индентификатор таблицы")
    private TableTemplateDto table;
    @Schema(description = "Данные для атоматического сбора и записи информации")
    private AutoDataCollectionDto autoDataCollection;
}