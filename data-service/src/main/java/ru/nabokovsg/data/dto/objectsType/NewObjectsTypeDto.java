package ru.nabokovsg.data.dto.objectsType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нового типа объекта")
public class NewObjectsTypeDto {

    @Schema(description = "Название объекта")
    @NotBlank(message = "name should not be blank")
    private String objectName;
    @Schema(description = "Положение объекта")
    private String orientation;
    @Schema(description = "Модель объекта")
    private String model;

    @Override
    public String toString() {
        return "NewObjectsTypeDto{" +
                "objectName='" + objectName + '\'' +
                ", orientation='" + orientation + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewObjectsTypeDto that = (NewObjectsTypeDto) o;
        return Objects.equals(objectName, that.objectName)
            && Objects.equals(orientation, that.orientation)
            && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectName, orientation, model);
    }
}