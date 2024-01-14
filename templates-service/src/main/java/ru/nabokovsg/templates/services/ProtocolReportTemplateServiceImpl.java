package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.dto.clientDto.ReportingDocumentDto;
import ru.nabokovsg.templates.dto.protocolReport.NewProtocolReportTemplateDto;
import ru.nabokovsg.templates.dto.protocolReport.ProtocolReportTemplateDto;
import ru.nabokovsg.templates.dto.protocolReport.ShortProtocolReportTemplateDto;
import ru.nabokovsg.templates.dto.protocolReport.UpdateProtocolReportTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.ProtocolReportTemplateMapper;
import ru.nabokovsg.templates.models.ConclusionTemplate;
import ru.nabokovsg.templates.models.ProtocolReportTemplate;
import ru.nabokovsg.templates.models.SubsectionTemplate;
import ru.nabokovsg.templates.models.TableTemplate;
import ru.nabokovsg.templates.repository.ProtocolReportTemplateRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProtocolReportTemplateServiceImpl implements ProtocolReportTemplateService {

    private final ProtocolReportTemplateRepository repository;
    private final ProtocolReportTemplateMapper mapper;
    private final SectionTemplateService sectionService;
    private final TemplateClient client;

    @Override
    public ShortProtocolReportTemplateDto save(Long sectionId, NewProtocolReportTemplateDto protocolDto) {
        ProtocolReportTemplate protocol = exists(sectionId, protocolDto.getReportingDocumentId());
        if (protocol == null) {
            ReportingDocumentDto document = client.getReportingDocument(protocolDto.getReportingDocumentId());
            document.setTitle(buildByReportingDocumentData(document.getTitle(), protocolDto.getSequentialNumber()));
            protocol = repository.save(mapper.mapToNewProtocolReportTemplate(protocolDto, document));
            sectionService.addProtocol(sectionId, protocol);
        }
        return mapper.mapToShortProtocolReportTemplateDto(protocol);
    }

    @Override
    public ShortProtocolReportTemplateDto update(UpdateProtocolReportTemplateDto protocolDto) {
        ProtocolReportTemplate protocol = getById(protocolDto.getId());
        ReportingDocumentDto document = client.getReportingDocument(protocolDto.getReportingDocumentId());
        document.setTitle(buildByReportingDocumentData(document.getTitle(), protocolDto.getSequentialNumber()));
        return mapper.mapToShortProtocolReportTemplateDto(
                repository.save(mapper.mapToUpdateProtocolReportTemplate(protocol, protocolDto, document))
        );
    }

    @Override
    public ProtocolReportTemplateDto get(Long id) {
        return mapper.mapToProtocolReportTemplateDto(getById(id));
    }

    @Override
    public List<ShortProtocolReportTemplateDto> getAll() {
        return repository.findAll().stream().map(mapper::mapToShortProtocolReportTemplateDto).toList();
    }

    @Override
    public void addTable(Long id, TableTemplate table) {
        ProtocolReportTemplate protocol = getById(id);
        protocol.getTables().add(table);
        repository.save(protocol);
    }

    @Override
    public void addSubsection(Long id, SubsectionTemplate subsection) {
        ProtocolReportTemplate protocol = getById(id);
        protocol.getSubsections().add(subsection);
        repository.save(protocol);
    }

    @Override
    public void addConclusion(Long id, ConclusionTemplate conclusion) {
        ProtocolReportTemplate protocol = getById(id);
        if (protocol.getConclusions() == null) {
            protocol.setConclusions(conclusion);
            repository.save(protocol);
        }
    }

    private ProtocolReportTemplate getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Protocol report template with id=%s not found", id))
        );
    }

    private String buildByReportingDocumentData(String title, Integer sequentialNumber) {
        return String.join(" ", title, "№", String.valueOf(sequentialNumber));
    }

    private ProtocolReportTemplate exists(Long sectionId, Long reportingDocumentId) {
        return repository.findProtocol(sectionId).stream()
                .collect(Collectors.toMap(ProtocolReportTemplate::getReportingDocumentId, p -> p))
                .get(reportingDocumentId);
    }
}