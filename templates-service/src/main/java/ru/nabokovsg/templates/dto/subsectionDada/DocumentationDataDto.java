package ru.nabokovsg.templates.dto.subsectionDada;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для получения нормативно-технической документации")
public class DocumentationDataDto {

    @Schema(description = "Идентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    @Positive(message = "object type id can only be positive")
    private Long objectTypeId;
    @Schema(description = "Указать методическую документацию")
    @NotNull(message = "Methodological document should not be null")
    private Boolean methodologicalDocument;
    @Schema(description = "Указать нормативную документацию")
    @NotNull(message = "Regulatory document should not be null")
    private Boolean regulatoryDocument;

    @Override
    public String toString() {
        return "DocumentationDataDto{" +
                "objectTypeId=" + objectTypeId +
                ", methodologicalDocument=" + methodologicalDocument +
                ", regulatoryDocument=" + regulatoryDocument +
                '}';
    }
}