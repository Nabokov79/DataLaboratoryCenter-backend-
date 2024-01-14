package ru.nabokovsg.data.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.contact.ContactDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие сведения об организации")
public class ShortOrganizationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное наименование организации")
    private String organization;
    @Schema(description = "Краткое наименование организации")
    private String shortNameOrganization;
    @Schema(description = "Реквизиты")
    private ContactDto contact;
}