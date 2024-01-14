package ru.nabokovsg.data.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.address.AddressDto;
import ru.nabokovsg.data.dto.branch.BranchDto;
import ru.nabokovsg.data.dto.contact.ContactDto;
import ru.nabokovsg.data.dto.license.LicenseDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные об организации")
public class OrganizationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное наименование организации")
    private String organization;
    @Schema(description = "Краткое наименование организации")
    private String shortNameOrganization;
    @Schema(description = "Адрес строения")
    private AddressDto address;
    @Schema(description = "Филиалы организации")
    private List<BranchDto> branches;
    @Schema(description = "Лицензия, аттестация")
    private List<LicenseDto> licenses;
    @Schema(description = "Реквизиты")
    private ContactDto contact;
}