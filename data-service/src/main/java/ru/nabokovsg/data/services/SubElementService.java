package ru.nabokovsg.data.services;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.data.dto.subElement.NewSubElementDto;
import ru.nabokovsg.data.dto.subElement.UpdateSubElementDto;
import ru.nabokovsg.data.models.SubElement;

import java.util.List;

@Validated
public interface SubElementService {

    List<SubElement> save(@Valid List<NewSubElementDto> subElementsDto);

    List<SubElement> update(@Valid List<UpdateSubElementDto> subElementsDto);
}