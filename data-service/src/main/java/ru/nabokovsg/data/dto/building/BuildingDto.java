package ru.nabokovsg.data.dto.building;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.data.dto.address.AddressDto;

@Setter
@Getter
@AllArgsConstructor
public class BuildingDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип строения")
    private String buildingType;
    @Schema(description = "Название")
    private String login;
    @Schema(description = "Адрес")
    private AddressDto address;
}