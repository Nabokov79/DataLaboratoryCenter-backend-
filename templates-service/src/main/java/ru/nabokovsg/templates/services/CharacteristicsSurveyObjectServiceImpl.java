package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.characteristics.CharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.NewCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.UpdateCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.CharacteristicsSurveyObjectMapper;
import ru.nabokovsg.templates.models.CharacteristicsSurveyObject;
import ru.nabokovsg.templates.repository.CharacteristicsSurveyObjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CharacteristicsSurveyObjectServiceImpl implements CharacteristicsSurveyObjectService {

    private final CharacteristicsSurveyObjectRepository repository;
    private final CharacteristicsSurveyObjectMapper mapper;
    private final SectionTemplateService sectionService;
    private final ProtocolTemplateService protocolService;

    @Override
    public List<CharacteristicsSurveyObjectDto> save(String string
                                                   , Long id
                                                   , List<NewCharacteristicsSurveyObjectDto> characteristicsDto) {
        Map<String, CharacteristicsSurveyObject> characteristicsDb = repository.findAll(
                characteristicsDto.stream()
                        .map(NewCharacteristicsSurveyObjectDto::getCharacteristicName)
                        .toList())
                .stream()
                .collect(Collectors.toMap(CharacteristicsSurveyObject::getCharacteristicName, c -> c)
        );
        if (!characteristicsDb.isEmpty()) {
            characteristicsDto = characteristicsDto.stream()
                                                 .filter(c -> !characteristicsDb.containsKey(c.getCharacteristicName()))
                                                 .toList();
        }
        if (!characteristicsDto.isEmpty()) {
            List<CharacteristicsSurveyObject> characteristics= repository.saveAll(
                                                    characteristicsDto.stream()
                                                                      .map(mapper::mapToNewCharacteristicsSurveyObject)
                                                                      .toList());
            for (CharacteristicsSurveyObject characteristic : characteristics) {
                characteristicsDb.put(characteristic.getCharacteristicName(), characteristic);
            }
        }
        switch (string) {
            case "section" -> sectionService.addCharacteristicsSurveyObject(
                    id, new ArrayList<>(characteristicsDb.values())
            );
            case "protocol" -> protocolService.addCharacteristicsSurveyObject(
                    id, new ArrayList<>(characteristicsDb.values())
            );
        }
        return characteristicsDb.values().stream()
                                         .map(mapper::mapToCharacteristicsSurveyObjectDto)
                                         .toList();
    }

    @Override
    public List<CharacteristicsSurveyObjectDto> update(List<UpdateCharacteristicsSurveyObjectDto> characteristicsDto) {
        validateIds(characteristicsDto.stream().map(UpdateCharacteristicsSurveyObjectDto::getId).toList());
        return repository.saveAll(characteristicsDto.stream()
                                                    .map(mapper::mapToUpdateCharacteristicsSurveyObject)
                                                    .toList()
                                    )
                                    .stream()
                                    .map(mapper::mapToCharacteristicsSurveyObjectDto)
                                    .toList();
    }

    @Override
    public List<CharacteristicsSurveyObjectDto> getAll() {
        return repository.findAll().stream()
                                   .map(mapper::mapToCharacteristicsSurveyObjectDto)
                                   .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(
                String.format("CharacteristicsSurveyObject template with id=%s not found for delete", id)
        );
    }

    private void validateIds(List<Long> ids) {
        Map<Long, CharacteristicsSurveyObject> characteristics = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(CharacteristicsSurveyObject::getId, n -> n));
        if (characteristics.size() != ids.size() || characteristics.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(characteristics.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(toList());
            throw new NotFoundException(
                    String.format("CharacteristicsSurveyObject templates with ids= %s not found", ids)
            );
        }
    }
}
