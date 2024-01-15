package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.recommendation.NewRecommendationTemplateDto;
import ru.nabokovsg.templates.dto.recommendation.RecommendationTemplateDto;
import ru.nabokovsg.templates.dto.recommendation.UpdateRecommendationTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.RecommendationTemplateMapper;
import ru.nabokovsg.templates.models.RecommendationTemplate;
import ru.nabokovsg.templates.models.enums.DataType;
import ru.nabokovsg.templates.repository.RecommendationTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationTemplateServiceImpl implements RecommendationTemplateService {

    private final RecommendationTemplateRepository repository;
    private final RecommendationTemplateMapper mapper;
    private final ProtocolTemplateService protocolService;
    private final SectionTemplateService sectionService;

    @Override
    public RecommendationTemplateDto save(DataType type, NewRecommendationTemplateDto recommendationDto) {
        RecommendationTemplate recommendation = repository.findByObjectTypeIdAndRecommendationText(
                                                                             recommendationDto.getObjectTypeId()
                                                                           , recommendationDto.getRecommendationText());
        if (recommendation == null) {
            recommendation = repository.save(mapper.mapToNewRecommendationTemplate(recommendationDto));
            switch (type) {
                case REPORT -> sectionService.addRecommendation(recommendationDto.getId(), recommendation);
                case PROTOCOL -> protocolService.addRecommendation(recommendationDto.getId(), recommendation);
            }
        }
        return mapper.mapToRecommendationTemplateDto(recommendation);
    }

    @Override
    public RecommendationTemplateDto update(UpdateRecommendationTemplateDto recommendationDto) {
        if (repository.existsById(recommendationDto.getId())) {
            return mapper.mapToRecommendationTemplateDto(
                    repository.save( mapper.mapToUpdateRecommendationTemplate(recommendationDto))
            );
        }
        throw new NotFoundException(
                String.format("Recommendation template with id=%s not found", recommendationDto.getId()));
    }

    @Override
    public List<RecommendationTemplateDto> getAll(Long objectTypeId) {
        return repository.findAllByObjectTypeId(objectTypeId).stream()
                                                             .map(mapper::mapToRecommendationTemplateDto)
                                                             .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw  new NotFoundException(String.format("Recommendation template with id=%s not found for delete", id));
    }
}