package ru.nabokovsg.templates.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.characteristics.CharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.NewCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.characteristics.UpdateCharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.CharacteristicsSurveyObjectMapper;
import ru.nabokovsg.templates.models.CharacteristicsSurveyObject;
import ru.nabokovsg.templates.models.QCharacteristicsSurveyObject;
import ru.nabokovsg.templates.models.enums.DataType;
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
    private final EntityManager entityManager;

    @Override
    public List<CharacteristicsSurveyObjectDto> save(Long objectTypeId
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
                                                                      .peek(c -> c.setObjectTypeId(objectTypeId))
                                                                      .toList());
            for (CharacteristicsSurveyObject characteristic : characteristics) {
                characteristicsDb.put(characteristic.getCharacteristicName(), characteristic);
            }
        }
        return characteristicsDb.values().stream()
                                         .map(mapper::mapToCharacteristicsSurveyObjectDto)
                                         .toList();
    }

    @Override
    public List<CharacteristicsSurveyObjectDto> update(Long objectTypeId
                                                     , List<UpdateCharacteristicsSurveyObjectDto> characteristicsDto) {
        validateIds(characteristicsDto.stream().map(UpdateCharacteristicsSurveyObjectDto::getId).toList());
        return repository.saveAll(characteristicsDto.stream()
                                                    .map(mapper::mapToUpdateCharacteristicsSurveyObject)
                                                    .peek(c -> c.setObjectTypeId(objectTypeId))
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

    @Override
    public List<CharacteristicsSurveyObject> getAllByPredicate(Long objectTypeId, DataType type) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QCharacteristicsSurveyObject.characteristicsSurveyObject.objectTypeId.eq(objectTypeId));
        switch (type) {
            case SECTION -> booleanBuilder.and(QCharacteristicsSurveyObject.characteristicsSurveyObject.useInReport.eq(true));
            case REPORT ->  booleanBuilder.and(QCharacteristicsSurveyObject.characteristicsSurveyObject.useInProtocol.eq(true));
        }
        QCharacteristicsSurveyObject characteristic = QCharacteristicsSurveyObject.characteristicsSurveyObject;
        List<CharacteristicsSurveyObject> characteristics = new JPAQueryFactory(entityManager).from(characteristic)
                .select(characteristic)
                .where(booleanBuilder)
                .fetch();
        if (characteristics.isEmpty()) {
            throw new NotFoundException(
                    String.format("Characteristics survey object for " +
                                   "objectType with id=%s and document type=%s not found", id, type));
        }
        return characteristics;
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
