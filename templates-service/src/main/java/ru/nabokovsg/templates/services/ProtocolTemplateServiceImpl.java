package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.dto.clientDto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.passportData.PredicateData;
import ru.nabokovsg.templates.dto.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.ShortProtocolTemplateDto;
import ru.nabokovsg.templates.dto.protocol.UpdateProtocolTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.templates.models.*;
import ru.nabokovsg.templates.repository.ProtocolTemplateRepository;
import ru.nabokovsg.templates.services.builders.StringBuilderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProtocolTemplateServiceImpl implements ProtocolTemplateService {

    private final ProtocolTemplateRepository repository;
    private final ProtocolTemplateMapper mapper;
    private final TemplateClient client;
    private final HeaderTemplateService headerService;
    private final StringBuilderService builderService;
    private final DataOfSurveyObjectTemplateService passportTemplateService;

    @Override
    public ShortProtocolTemplateDto save(NewProtocolTemplateDto protocolDto) {
        ProtocolTemplate template = repository.findByReportingDocumentIdAndObjectTypeId(
                                                                                  protocolDto.getReportingDocumentId()
                                                                                , protocolDto.getObjectTypeId());
        if (template == null) {
            template = mapper.mapToNewProtocolTemplate(protocolDto
                    , headerService.save(protocolDto.getHeader()));
            template = mapper.mapToProtocolTemplate(template
                             , builderService.buildFromObjectsType(client.getObjectsType(protocolDto.getObjectTypeId()))
                             , getReportingDocument(protocolDto.getReportingDocumentId())
                             , getDataOfSurveyObjectPassportTemplate(protocolDto.getObjectTypeId()
                            , protocolDto.isProtocolSurvey()
                            , protocolDto.isProtocolQuality()));
        }
        return mapper.mapToShortProtocolTemplateDto(repository.save(template));
    }

    @Override
    public ShortProtocolTemplateDto update(UpdateProtocolTemplateDto protocolDto) {
        if (repository.existsById(protocolDto.getId())) {
            ProtocolTemplate template = mapper.mapToUpdateProtocolTemplate(protocolDto
                                                                , headerService.save(protocolDto.getHeader()));
            template = mapper.mapToProtocolTemplate(template
                    , builderService.buildFromObjectsType(client.getObjectsType(protocolDto.getObjectTypeId()))
                    , getReportingDocument(protocolDto.getReportingDocumentId())
                    , template.getCharacteristics());
            return mapper.mapToShortProtocolTemplateDto(repository.save(template));
        }
        throw new NotFoundException(
                String.format("Protocol template by id=%s not found for update", protocolDto.getId())
        );
    }

    @Override
    public ProtocolTemplateDto get(Long id) {
        return mapper.mapToProtocolTemplateDto(getById(id));
    }

    @Override
    public List<ShortProtocolTemplateDto> getAll() {
        List<ProtocolTemplate> templates = repository.findAll();
        if (templates.isEmpty()) {
            throw new NotFoundException(String.format("<Protocols templates %s not found", templates));
        }
        return templates.stream().map(mapper::mapToShortProtocolTemplateDto).toList();
    }

    @Override
    public void addTable(Long id, TableTemplate table) {
        ProtocolTemplate protocol = getById(id);
        protocol.getTables().add(table);
        repository.save(protocol);
    }

    @Override
    public void addSubsection(Long id, SubsectionTemplate subsection) {
        ProtocolTemplate protocol = getById(id);
        protocol.getSubsections().add(subsection);
        repository.save(protocol);
    }

    @Override
    public void addConclusion(Long id, ConclusionTemplate conclusion) {
        ProtocolTemplate protocol = getById(id);
        protocol.setConclusions(conclusion);
        repository.save(protocol);
    }

    @Override
    public void addAppendices(Long id, AppendicesTemplate appendices) {
        ProtocolTemplate protocol = getById(id);
        protocol.getAppendices().add(appendices);
        repository.save(protocol);
    }

    private ProtocolTemplate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Protocol template with id=%s not found", id)));
    }

    private ReportingDocumentDto getReportingDocument(Long id) {
        return client.getReportingDocument(id);
    }

    private List<DataOfSurveyObjectTemplate> getDataOfSurveyObjectPassportTemplate(Long objectTypeId
                                                                                  , boolean isProtocolSurvey
                                                                                  , boolean isProtocolQuality) {
        return passportTemplateService.getAllByPredicate(objectTypeId
                                            , new PredicateData(false, isProtocolSurvey, isProtocolQuality));
    }
}