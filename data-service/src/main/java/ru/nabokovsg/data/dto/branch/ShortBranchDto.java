package ru.nabokovsg.data.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.data.dto.contact.ContactDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Краткие сведения о филиале организации")
public class ShortBranchDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String branch;
    @Schema(description = "Краткое название")
    private String shortNameBranch;
    @Schema(description = "Контакты")
    private ContactDto contact;
}