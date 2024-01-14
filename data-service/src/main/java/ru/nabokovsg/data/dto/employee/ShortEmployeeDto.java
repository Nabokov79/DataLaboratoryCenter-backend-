package ru.nabokovsg.data.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.contact.ContactDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткая информация о сотруднике")
public class ShortEmployeeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Отчество")
    private String patronymic;
    @Schema(description = "Фамилия")
    private String surname;
    @Schema(description = "Должность")
    private String post;
    @Schema(description = "Контактные данные сотрудника")
    private ContactDto contact;
}