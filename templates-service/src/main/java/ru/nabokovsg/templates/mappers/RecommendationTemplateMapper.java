package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.templates.dto.recommendation.NewRecommendationTemplateDto;
import ru.nabokovsg.templates.dto.recommendation.RecommendationTemplateDto;
import ru.nabokovsg.templates.models.RecommendationTemplate;

@Mapper(componentModel = "spring")
public interface RecommendationTemplateMapper {

    RecommendationTemplate mapToNewRecommendationTemplate(NewRecommendationTemplateDto recommendationDto);

    @Mapping(source = "text", target = "recommendationText")
    RecommendationTemplate mapToUpdateRecommendationTemplate(@MappingTarget RecommendationTemplate recommendation
                                                                          , String text);

    RecommendationTemplateDto mapToRecommendationTemplateDto(RecommendationTemplate recommendation);
}