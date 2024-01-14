package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.objectsSurveyElementData.NewObjectsSurveyElementDataDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.SurveyObjectElementDataDto;
import ru.nabokovsg.data.dto.objectsSurveyElementData.UpdateObjectsSurveyElementDataDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.ObjectsSurveyElementDataMapper;
import ru.nabokovsg.data.models.Element;
import ru.nabokovsg.data.models.ObjectsSurveyElementData;
import ru.nabokovsg.data.models.SubElement;
import ru.nabokovsg.data.models.SurveyObjectElementData;
import ru.nabokovsg.data.repository.ObjectsSurveyElementDataRepository;
import ru.nabokovsg.data.services.builder.RepositoryRequestService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectsSurveyElementDataServiceImpl implements ObjectsSurveyElementDataService {

    private final ObjectsSurveyElementDataRepository repository;
    private final ObjectsSurveyElementDataMapper mapper;
    private final RepositoryRequestService requestService;

    @Override
    public List<SurveyObjectElementDataDto> save(Long surveyObjectId, List<NewObjectsSurveyElementDataDto> elementsDataDto) {
        ObjectsSurveyElementData data = getDataForObjectsSurveyElement(surveyObjectId
                                                                     , elementsDataDto
                                                                          .stream()
                                                                          .map(NewObjectsSurveyElementDataDto::getElementId)
                                                                          .toList());
        return getListDto(repository.saveAll(elementsDataDto.stream().map(e -> {
            SurveyObjectElementData elementData = mapper.mapFromNewObjectsSurveyElement(e);
            elementData.setElement(data.getElements().get(e.getElementId()));
            elementData.setSubElement(data.getSubElements().get(e.getSubElementId()));
            elementData.setObjectSurvey(data.getObjectSurvey());
            return elementData;
        }).toList()));
    }

    @Override
    public List<SurveyObjectElementDataDto> update(Long surveyObjectId, List<UpdateObjectsSurveyElementDataDto> elementsDataDto) {
        validateIds(elementsDataDto.stream().map(UpdateObjectsSurveyElementDataDto::getId).toList());
        ObjectsSurveyElementData data = getDataForObjectsSurveyElement(surveyObjectId
                                                               , elementsDataDto
                                                                       .stream()
                                                                       .map(UpdateObjectsSurveyElementDataDto::getElementId)
                                                                       .toList());
        return getListDto(repository.saveAll(elementsDataDto.stream().map(e -> {
            SurveyObjectElementData elementData = mapper.mapFromUpdateObjectsSurveyElement(e);
            elementData.setElement(data.getElements().get(e.getElementId()));
            elementData.setSubElement(data.getSubElements().get(e.getSubElementId()));
            elementData.setObjectSurvey(data.getObjectSurvey());
            return elementData;
        }).toList()));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("ObjectsSurvey elements with ids= %s not found for delete", id));
    }

    private ObjectsSurveyElementData getDataForObjectsSurveyElement(Long surveyObjectId, List<Long> elementIds) {
        ObjectsSurveyElementData data = new ObjectsSurveyElementData();
        data.setObjectSurvey(requestService.getSurveyObject(surveyObjectId));
        data.setElements(requestService.getElement(elementIds).stream()
                                                            .collect(Collectors.toMap(Element::getId, e -> e)));
        data.setSubElements(data.getElements().values().stream()
                .map(Element::getSubElements)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toMap(SubElement::getId, s -> s)));
        return data;
    }


    private void validateIds(List<Long> ids) {
        Map<Long, SurveyObjectElementData> elements = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SurveyObjectElementData::getId, e -> e));
        if (elements.size() != ids.size() || elements.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(elements.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("ObjectsSurvey elements with ids= %s not found", ids));
        }
    }

    private List<SurveyObjectElementDataDto> getListDto(List<SurveyObjectElementData> elements) {
        return elements.stream().map(mapper::mapFromObjectsSurveyElementDto).toList();
    }
}