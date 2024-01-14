package ru.nabokovsg.templates.dto.clientDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
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