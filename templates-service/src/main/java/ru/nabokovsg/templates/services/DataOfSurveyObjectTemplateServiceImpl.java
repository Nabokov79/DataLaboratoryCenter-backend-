package ru.nabokovsg.templates.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.passportData.DataOfSurveyObjectTemplateDto;
import ru.nabokovsg.templates.dto.passportData.NewDataOfSurveyObjectTemplate;
import ru.nabokovsg.templates.dto.passportData.PredicateData;
import ru.nabokovsg.templates.dto.passportData.UpdateDataOfSurveyObjectTemplate;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.DataOfSurveyObjectTemplateMapper;
import ru.nabokovsg.templates.models.DataOfSurveyObjectTemplate;
import ru.nabokovsg.templates.models.QDataOfSurveyObjectPassportTemplate;
import ru.nabokovsg.templates.repository.DataOfSurveyObjectTemplateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DataOfSurveyObjectTemplateServiceImpl implements DataOfSurveyObjectTemplateService {

    private final DataOfSurveyObjectTemplateRepository repository;
    private final DataOfSurveyObjectTemplateMapper mapper;
    private final EntityManager entityManager;

    @Override
    public List<DataOfSurveyObjectTemplateDto> save(Long objectTypeId
                                                   , List<NewDataOfSurveyObjectTemplate> characteristicsDto) {
        Map<String, DataOfSurveyObjectTemplate> characteristicsDb = getDuplicates(objectTypeId
                , characteristicsDto.stream()
                                    .map(NewDataOfSurveyObjectTemplate::getCharacteristicName)
                                    .toList())
                .stream()
                .collect(Collectors.toMap(DataOfSurveyObjectTemplate::getCharacteristicName, c -> c)
        );
        if (!characteristicsDb.isEmpty()) {
            characteristicsDto = characteristicsDto.stream()
                                                 .filter(c -> !characteristicsDb.containsKey(c.getCharacteristicName()))
                                                 .toList();
        }
        if (!characteristicsDto.isEmpty()) {
            List<DataOfSurveyObjectTemplate> characteristics= repository.saveAll(
                                                    characteristicsDto.stream()
                                                                      .map(mapper::mapToNewCharacteristicsSurveyObject)
                                                                      .peek(c -> c.setObjectTypeId(objectTypeId))
                                                                      .distinct()
                                                                      .toList());
            for (DataOfSurveyObjectTemplate characteristic : characteristics) {
                characteristicsDb.put(characteristic.getCharacteristicName(), characteristic);
            }
        }
        return characteristicsDb.values().stream()
                                         .map(mapper::mapToCharacteristicsSurveyObjectDto)
                                         .toList();
    }

    @Override
    public List<DataOfSurveyObjectTemplateDto> update(Long objectTypeId
                                                  , List<UpdateDataOfSurveyObjectTemplate> characteristicsDto) {
        validateIds(characteristicsDto.stream().map(UpdateDataOfSurveyObjectTemplate::getId).toList());
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
    public List<DataOfSurveyObjectTemplateDto> getAll() {
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
    public List<DataOfSurveyObjectTemplate> getAllByPredicate(Long objectTypeId, PredicateData data) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QDataOfSurveyObjectTemplate.dataOfSurveyObjectPassportTemplate.objectTypeId
                                                                                    .eq(objectTypeId));
        booleanBuilder.and(QDataOfSurveyObjectTemplate.dataOfSurveyObjectPassportTemplate.useInReport
                                                                                    .eq(data.isUseInReport()));
        booleanBuilder.and(QDataOfSurveyObjectTemplate.dataOfSurveyObjectPassportTemplate.useInProtocolSurvey
                                                                                    .eq(data.isUseInProtocolSurvey()));
        booleanBuilder.and(QDataOfSurveyObjectTemplate.dataOfSurveyObjectPassportTemplate.useInProtocolQuality
                                                                                    .eq(data.isUseInProtocolQuality()));
        QDataOfSurveyObjectTemplate passport
                                            = QDataOfSurveyObjectTemplate.dataOfSurveyObjectPassportTemplate;
        List<DataOfSurveyObjectTemplate> characteristics = new JPAQueryFactory(entityManager).from(passport)
                .select(passport)
                .where(booleanBuilder)
                .fetch();
        if (characteristics.isEmpty()) {
            throw new NotFoundException(
                    String.format("Characteristics survey object for " +
                                   "objectT tpe with id=%s and predicate=%s not found", objectTypeId, data));
        }
        return characteristics;
    }

    private List<DataOfSurveyObjectTemplate> getDuplicates(Long objectTypeId, List<String> names) {
        QDataOfSurveyObjectTemplate data = QDataOfSurveyObjectTemplate.dataOfSurveyObjectPassportTemplate;
        return new JPAQueryFactory(entityManager)
                .from(data)
                .select(data)
                .where(data.objectTypeId.eq(objectTypeId), data.characteristicName.in(names))
                .fetch();
    }
    private void validateIds(List<Long> ids) {
        Map<Long, DataOfSurveyObjectTemplate> characteristics = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(DataOfSurveyObjectTemplate::getId, n -> n));
        if (characteristics.size() != ids.size() || characteristics.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(characteristics.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(toList());
            throw new NotFoundException(
                    String.format("CharacteristicsSurveyObject templates with ids= %s not found", ids)
            );
        }
    }
}
