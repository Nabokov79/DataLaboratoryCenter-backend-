package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.element.ElementDto;
import ru.nabokovsg.data.dto.element.NewElementDto;
import ru.nabokovsg.data.dto.element.UpdateElementDto;
import ru.nabokovsg.data.dto.objectsType.ObjectsTypeElementsDto;
import ru.nabokovsg.data.dto.subElement.NewSubElementDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.ElementMapper;
import ru.nabokovsg.data.mappers.ObjectsTypeMapper;
import ru.nabokovsg.data.models.Element;
import ru.nabokovsg.data.models.SubElement;
import ru.nabokovsg.data.repository.ElementRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ElementServiceImpl implements ElementService {

    private final ElementRepository repository;
    private final ElementMapper mapper;
    private final SubElementService subElementService;
    private final ObjectsTypeService objectsTypeService;
    private final ObjectsTypeMapper objectsTypeMapper;

    @Override
    public List<ObjectsTypeElementsDto> save(List<Long> objectsTypeId, List<NewElementDto> elementsDto) {
        List<SubElement> subElementsDb = subElementService.save(elementsDto.stream()
                                                                            .map(NewElementDto::getSubElements)
                                                                            .filter(Objects::nonNull)
                                                                            .flatMap(Collection::stream)
                                                                            .toList());
        Set<Element> elementsDb = repository.findAllByObjectsTypeId(objectsTypeId);
        List<Element> elements = new ArrayList<>();
        if(elementsDb.isEmpty()) {
            for (Long id : objectsTypeId) {
                elements.addAll(elementsDto.stream().map(e -> {
                    Element element = mapper.mapToNewElement(e);
                    element.setObjectsTypeId(id);
                    if (e.getSubElements() != null) {
                        List<String> subElementNames = e.getSubElements().stream()
                                .map(NewSubElementDto::getSubElementName)
                                .toList();
                        element.setSubElements(subElementsDb.stream()
                                .filter(s -> subElementNames.contains(s.getSubElementName()))
                                .toList());
                    }
                    return element;
                }).toList());
            }
        } else {
            Map<Long, List<Element>> elementsD = new HashMap<>();
            for (Long id : objectsTypeId) {
                elementsD.put(id, elementsDb.stream().filter(o -> o.getObjectsTypeId().equals(id)).toList());
            }
            for (Long id : objectsTypeId) {
                List<String> elementNames = elementsD.get(id).stream().map(Element::getElementName).toList();
                List<Element> elementList = elementsDto.stream()
                                                        .filter(o -> !elementNames.contains(o.getElementName()))
                                                        .map(mapper::mapToNewElement)
                                                        .toList();
                if (!elementList.isEmpty()) {
                    elements.addAll(elementList);
                }
            }
        }
        if (!elementsDb.isEmpty()) {
            elements = elementsDb.stream().toList();
        } else {
            elements = repository.saveAll(elements);
        }
        return objectsTypeService.addElements(objectsTypeId, elements);
    }

    @Override
    public List<ElementDto> update(List<UpdateElementDto> elementsDto) {
        validateIds(elementsDto.stream().map(UpdateElementDto::getId).toList());
        List<SubElement> subElementsDb = subElementService.update(elementsDto.stream()
                .map(UpdateElementDto::getSubElements)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList());
        List<Element> elementsDb = elementsDto.stream()
                                              .map(e -> {
                                                  Element element = mapper.mapToUpdateElement(e);
                                                  List<String> subElementNames = element.getSubElements().stream()
                                                          .map(SubElement::getSubElementName)
                                                          .toList();
                                                  element.setSubElements(subElementsDb.stream()
                                                          .filter(s -> subElementNames.contains(s.getSubElementName()))
                                                          .toList());
                                                    return element;
                                                })
                                                .toList();
        return repository.saveAll(elementsDb)
                         .stream()
                         .map(mapper::mapToElementDto)
                         .toList();
    }

    @Override
    public ObjectsTypeElementsDto getAll(Long objectsTypeId) {
        return objectsTypeMapper.mapToObjectTypeElementsDto(objectsTypeService.getById(objectsTypeId));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Element> elements = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Element::getId, n -> n));
        if (elements.size() != ids.size() || elements.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(elements.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(toList());
            throw new NotFoundException(String.format("elements with ids= %s not found", ids));
        }
    }
}