package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.characteristics.CharacteristicsSurveyObjectDto;
import ru.nabokovsg.templates.dto.section.NewSectionTemplateDto;
import ru.nabokovsg.templates.dto.section.SectionTemplateDto;
import ru.nabokovsg.templates.dto.section.UpdateSectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.ShortSubsectionTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.SectionTemplateMapper;
import ru.nabokovsg.templates.models.*;
import ru.nabokovsg.templates.models.enums.DataType;
import ru.nabokovsg.templates.repository.SectionTemplateRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionTemplateServiceImpl implements SectionTemplateService {

    private final SectionTemplateRepository repository;
    private final SectionTemplateMapper mapper;
    private final ReportTemplateService reportService;
    private final CharacteristicsSurveyObjectService characteristicsService;
    private final PageTitleTemplateService pageTitleService;

    @Override
    public List<SectionTemplateDto> save(Long reportId, List<NewSectionTemplateDto> sectionsDto) {
        List<SectionTemplate> sections = reportService.existsSubsectionsByReportId(reportId);
        if (!sections.isEmpty()) {
            List<String> sectionNames = sections.stream().map(SectionTemplate::getSectionName).toList();
            sectionsDto = sectionsDto.stream().filter(s -> !sectionNames.contains(s.getSectionName())).toList();
        }
        if (sectionsDto.isEmpty()) {
            return sections.stream()
                             .map(mapper::mapToSectionTemplateDto)
                             .sorted(Comparator.comparing(SectionTemplateDto::getId))
                             .toList();
        }
        sections.addAll(repository.saveAll(sectionsDto.stream().map(s -> {
            SectionTemplate section = mapper.mapToNewSectionTemplate(s);
            if (s.isCharacteristics()) {
                section.setCharacteristics(getCharacteristicsSurveyObject(
                        pageTitleService.getObjectTypeIdByReportId(reportId))
                );
            }
            return section;
        }).toList()));
        reportService.saveWithSectionTemplate(reportId, sections);
        return sections.stream()
                .map(mapper::mapToSectionTemplateDto)
                .sorted(Comparator.comparing(SectionTemplateDto::getId))
                .toList();
    }

    @Override
    public List<SectionTemplateDto> update(List<UpdateSectionTemplateDto> sectionsDto) {
        List<Long> ids = sectionsDto.stream().map(UpdateSectionTemplateDto::getId).toList();
        validateIds(ids);
        List<SectionTemplate> sections =  repository.findAllById(ids);
        Map<Long, SectionTemplate> sectionsDb = sections.stream()
                                                        .collect(Collectors.toMap(SectionTemplate::getId, s -> s));
        List<CharacteristicsSurveyObject> characteristics = sections.stream()
                                                                    .map(SectionTemplate::getCharacteristics)
                                                                    .filter(Objects::nonNull)
                                                                    .flatMap(Collection::stream)
                                                                    .toList();
        return repository.saveAll(sectionsDto.stream()
                        .map(s ->  {
                            SectionTemplate section = mapper.mapToUpdateSectionTemplate(sectionsDb.get(s.getId()), s);
                            if (s.isCharacteristics()) {
                                section.setCharacteristics(characteristics);
                            }
                            return section;
                        })
                        .toList())
                        .stream()
                        .map(mapper::mapToSectionTemplateDto)
                        .toList();
    }

    @Override
    public SectionTemplateDto get(Long id) {
        SectionTemplateDto section = mapper.mapToSectionTemplateDto(getById(id));
        if (!section.getCharacteristics().isEmpty()) {
            section.setCharacteristics(
                    section.getCharacteristics().stream()
                            .sorted(Comparator.comparing(CharacteristicsSurveyObjectDto::getId))
                            .toList());
        }
        return section;
    }

    @Override
    public List<ShortSubsectionTemplateDto> getAllSubsections(Long id) {
        return repository.findAllSubsection(id).stream()
                                               .map(mapper::mapToShortSubsectionTemplateDto)
                                               .toList();
    }

    @Override
    public void addSubsection(Long id, SubsectionTemplate subsection) {
        SectionTemplate section = getById(id);
        section.getSubsections().add(subsection);
        repository.save(section);
    }

    @Override
    public void addProtocol(Long id, ProtocolReportTemplate protocol) {
        SectionTemplate section = getById(id);
        section.getProtocols().add(protocol);
        repository.save(section);
    }

    @Override
    public void addCharacteristicsSurveyObject(Long id, List<CharacteristicsSurveyObject> characteristics) {
        SectionTemplate section = getById(id);
        section.getCharacteristics().addAll(characteristics);
        repository.save(section);
    }

    @Override
    public void addRecommendation(Long id, List<RecommendationTemplate> recommendations) {
        SectionTemplate section = getById(id);
        section.getRecommendations().addAll(recommendations);
        repository.save(section);
    }

    private SectionTemplate getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        String.format(String.format("Section template with id= %s not found", id))
                )
        );
    }

    private void validateIds(List<Long> ids) {
        Map<Long, SectionTemplate> sections = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(SectionTemplate::getId, m -> m));
        if (sections.size() != ids.size() || sections.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(sections.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("Section template with ids= %s not found", ids));
        }
    }

    private List<CharacteristicsSurveyObject> getCharacteristicsSurveyObject(Long objectTypeId) {
        return characteristicsService.getAllByPredicate(objectTypeId, DataType.SECTION);
    }
}
