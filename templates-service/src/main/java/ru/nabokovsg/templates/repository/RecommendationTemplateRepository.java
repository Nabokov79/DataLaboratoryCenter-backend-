package ru.nabokovsg.templates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.templates.models.RecommendationTemplate;

import java.util.Set;

public interface RecommendationTemplateRepository extends JpaRepository<RecommendationTemplate, Long> {

    RecommendationTemplate findByObjectTypeIdAndRecommendationText(Long objectTypeId, String recommendationText);

    Set<RecommendationTemplate> findAllByObjectTypeId(Long objectTypeId);
}