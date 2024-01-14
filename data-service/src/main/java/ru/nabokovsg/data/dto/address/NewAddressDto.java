package ru.nabokovsg.data.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные нового адреса")
public class NewAddressDto {

    @Schema(description = "Индентификатор населенного пункта")
    @NotBlank(message = "city should not be blank")
    private String city;
    @Schema(description = "Почтовый индекс")
    private Integer index;
    @Schema(description = "Название улицы")
    @NotBlank(message = "street name should not be blank")
    private String street;
    @Schema(description = "Номер дома")
    @NotNull(message = "house number should not be blank")
    @Positive(message = "house number can only be positive")
    private Integer houseNumber;
    @Schema(description = "Номер корпуса дома")
    @Positive(message = "building number can only be positive")
    private Integer buildingNumber;
    @Schema(description = "Литера дома")
    private String letter;
}