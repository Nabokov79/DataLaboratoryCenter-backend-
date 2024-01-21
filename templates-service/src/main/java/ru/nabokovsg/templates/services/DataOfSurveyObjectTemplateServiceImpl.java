package ru.nabokovsg.templates.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.passportData.*;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.DataOfSurveyObjectTemplateMapper;
import ru.nabokovsg.templates.models.DataOfSurveyObjectTemplate;
import ru.nabokovsg.templates.models.QDataOfSurveyObjectTemplate;
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
    public List<DataOfSurveyObjectTemplateDto> save(NewDataOfSurveyObjectTemplateDto dataOfSurveyObjectDto) {
        PredicateData predicate = mapper.mapToPredicateDataForNewData(dataOfSurveyObjectDto);
        Map<String, DataOfSurveyObjectTemplate> characteristicsDb = getAllByPredicate(predicate)
                .stream()
                .collect(Collectors.toMap(DataOfSurveyObjectTemplate::getCharacteristicName, c -> c)
        );
        List<NewPassportDataTemplateDto> passportData = dataOfSurveyObjectDto.getPassportData();
        if (!characteristicsDb.isEmpty()) {
            passportData = passportData.stream().filter(c -> !characteristicsDb.containsKey(c.getCharacteristicName()))
                                                 .toList();
        }
        if (!passportData.isEmpty()) {
            List<DataOfSurveyObjectTemplate> characteristics = repository.saveAll(
                                          passportData.stream()
                                                      .map(d -> mapper.mapToNewDataOfSurveyObjectTemplate(d, predicate))
                                                      .distinct()
                                                      .toList());
            for (DataOfSurveyObjectTemplate characteristic : characteristics) {
                characteristicsDb.put(characteristic.getCharacteristicName(), characteristic);
            }
        }
        return characteristicsDb.values().stream()
                                         .map(mapper::mapToDataOfSurveyObjectTemplateDto)
                                         .toList();
    }

    @Override
    public List<DataOfSurveyObjectTemplateDto> update(UpdateDataOfSurveyObjectTemplateDto dataOfSurveyObjectDto) {
        validateIds(dataOfSurveyObjectDto.getPassportData().stream().map(UpdatePassportDataTemplateDto::getId).toList());
        PredicateData predicate = mapper.mapToPredicateDataForUpdateData(dataOfSurveyObjectDto);
        List<UpdatePassportDataTemplateDto> passportData = dataOfSurveyObjectDto.getPassportData();
        return repository.saveAll(passportData.stream()
                                                    .map(d -> mapper.mapToUpdateDataOfSurveyObjectTemplate(d, predicate))
                                                    .toList()
                                    )
                                    .stream()
                                    .map(mapper::mapToDataOfSurveyObjectTemplateDto)
                                    .toList();
    }

    @Override
    public List<DataOfSurveyObjectTemplateDto> getAll() {
        return repository.findAll().stream()
                                   .map(mapper::mapToDataOfSurveyObjectTemplateDto)
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
    public List<DataOfSurveyObjectTemplate> getAllDataOfSurveyObjectTemplate(PredicateData predicate) {
        List<DataOfSurveyObjectTemplate> templates = getAllByPredicate(predicate);
        if (templates.isEmpty()) {
            throw new NotFoundException(
                    String.format("Data of survey object template by predicate=%s not found", predicate));
        }
        return templates;
    }

    private List<DataOfSurveyObjectTemplate> getAllByPredicate(PredicateData data) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QDataOfSurveyObjectTemplate.dataOfSurveyObjectTemplate.objectTypeId.eq(data.getObjectTypeId()));
        booleanBuilder.and(QDataOfSurveyObjectTemplate.dataOfSurveyObjectTemplate.useInReport.eq(data.isUseInReport()));
        booleanBuilder.and(QDataOfSurveyObjectTemplate.dataOfSurveyObjectTemplate.useInProtocolSurvey
                                                                                    .eq(data.isUseInProtocolSurvey()));
        booleanBuilder.and(QDataOfSurveyObjectTemplate.dataOfSurveyObjectTemplate.useInProtocolQuality
                                                                                    .eq(data.isUseInProtocolQuality()));
        QDataOfSurveyObjectTemplate passport = QDataOfSurveyObjectTemplate.dataOfSurveyObjectTemplate;
        return new JPAQueryFactory(entityManager).from(passport)
                .select(passport)
                .where(booleanBuilder)
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
