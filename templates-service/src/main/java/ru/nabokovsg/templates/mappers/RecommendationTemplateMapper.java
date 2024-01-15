package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.templates.dto.recommendation.NewRecommendationTemplateDto;
import ru.nabokovsg.templates.dto.recommendation.RecommendationTemplateDto;
import ru.nabokovsg.templates.dto.recommendation.UpdateRecommendationTemplateDto;
import ru.nabokovsg.templates.models.RecommendationTemplate;

@Mapper(componentModel = "spring")
public interface RecommendationTemplateMapper {

    RecommendationTemplate mapToNewRecommendationTemplate(NewRecommendationTemplateDto recommendationDto);

    RecommendationTemplate mapToUpdateRecommendationTemplate(UpdateRecommendationTemplateDto recommendationDto);

    RecommendationTemplateDto mapToRecommendationTemplateDto(RecommendationTemplate recommendation);
}