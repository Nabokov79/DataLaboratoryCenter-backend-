package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.NewConclusionTemplateDto;
import ru.nabokovsg.templates.dto.conclusion.UpdateConclusionTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.ConclusionTemplateMapper;
import ru.nabokovsg.templates.models.ConclusionTemplate;
import ru.nabokovsg.templates.models.enums.DataType;
import ru.nabokovsg.templates.repository.ConclusionTemplateRepository;

@Service
@RequiredArgsConstructor
public class ConclusionTemplateServiceImpl implements ConclusionTemplateService {

    private final ConclusionTemplateRepository repository;
    private final ConclusionTemplateMapper mapper;
    private final ProtocolTemplateService protocolService;
    private final ProtocolReportTemplateService protocolReportService;

    @Override
    public ConclusionTemplateDto save(DataType type, NewConclusionTemplateDto conclusionDto) {
        ConclusionTemplate conclusion = null;
        switch (type) {
            case PROTOCOL -> conclusion = repository.findByProtocolId(conclusionDto.getProtocolId());
            case PROTOCOL_REPORT -> conclusion = repository.findByProtocolReportId(conclusionDto.getProtocolId());
        }
        if (conclusion == null) {
            conclusion = repository.save(mapper.mapToNewConclusionTemplate(conclusionDto));
        }

        switch (type) {
            case PROTOCOL ->  protocolService.addConclusion(conclusionDto.getProtocolId(), conclusion);
            case PROTOCOL_REPORT -> protocolReportService.addConclusion(conclusionDto.getProtocolId(), conclusion);
        }
        return mapper.mapToConclusionTemplateDto(conclusion);
    }

    @Override
    public ConclusionTemplateDto update(UpdateConclusionTemplateDto conclusionDto) {
        return mapper.mapToConclusionTemplateDto(
                repository.save(mapper.mapToUpdateConclusionTemplate(get(conclusionDto.getId()), conclusionDto))
        );
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw  new NotFoundException(String.format("Conclusion template with id=%s not found for delete", id));
    }

    private ConclusionTemplate get(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Conclusion template with id=%s not found", id))
        );
    }
}