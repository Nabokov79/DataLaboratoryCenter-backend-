package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.subElement.NewSubElementDto;
import ru.nabokovsg.data.dto.subElement.UpdateSubElementDto;
import ru.nabokovsg.data.models.SubElement;

@Mapper(componentModel = "spring")
public interface SubElementMapper {

    SubElement mapToNewSubElement(NewSubElementDto subElementDto);

    SubElement mapToUpdateSubElements(UpdateSubElementDto subElementDto);
}