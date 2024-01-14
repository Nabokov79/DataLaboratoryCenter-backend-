package ru.nabokovsg.templates.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.templates.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.NewConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.UpdateConclusionTemplateDto;
import ru.nabokovsg.templates.models.ConclusionTemplate;

@Mapper(componentModel = "spring")
public interface ConclusionTemplateMapper {

    ConclusionTemplate mapToNewConclusionTemplate(NewConclusionTemplateDto conclusionDto);

    @Mapping(source = "conclusionDto.ifThanNorm", target = "ifThanNorm")
    @Mapping(source = "conclusionDto.approaching", target = "approaching")
    @Mapping(source = "conclusionDto.equal", target = "equal")
    @Mapping(source = "conclusionDto.ifLessNorm", target = "ifLessNorm")
    @Mapping(source = "conclusionDto.ifNoNorm", target = "ifNoNorm")
    ConclusionTemplate mapToUpdateConclusionTemplate(@MappingTarget ConclusionTemplate conclusion
                                                                  , UpdateConclusionTemplateDto conclusionDto);

    ConclusionTemplateDto mapToConclusionTemplateDto(ConclusionTemplate conclusion);
}