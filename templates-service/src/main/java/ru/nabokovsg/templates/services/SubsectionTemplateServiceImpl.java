package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.autoData.AutoDataCollectionDto;
import ru.nabokovsg.templates.dto.subsection.NewSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.SubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsection.UpdateSubsectionTemplateDto;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.DocumentationDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.MeasuringToolDataDto;
import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.SubsectionTemplateMapper;
import ru.nabokovsg.templates.models.SubsectionTemplate;
import ru.nabokovsg.templates.models.TableTemplate;
import ru.nabokovsg.templates.models.enums.DataType;
import ru.nabokovsg.templates.repository.SubsectionTemplateRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;
    private final SubsectionDataProcessingService service;
    private final SectionTemplateService sectionService;
    private final ProtocolTemplateService protocolService;
    private final ProtocolReportTemplateService protocolReportService;
    private final AutoDataCollectionService autoDataCollectionService;

    @Override
    public SubsectionTemplateDto save(DataType type, Long id, NewSubsectionTemplateDto subsectionDto) {
        SubsectionTemplate subsection = exists(type, id, subsectionDto.getSubsectionName());
        boolean flag = false;
        if (subsection == null) {
            subsection = mapper.mapToNewSubsectionTemplate(subsectionDto);
            flag = true;
        }
        if (subsection.getDivisionData() == null && subsectionDto.getDivision() != null) {
           setDivisionData(subsection, subsectionDto.getDivision());
        }
        if (subsection.getDocumentation() == null && subsectionDto.getDocumentation() != null) {
            setDocumentationData(subsection, subsectionDto.getDocumentation());
        }
        if (subsection.getMeasuringTools() == null && subsectionDto.getMeasuringTools() != null) {
           setMeasuringToolData(subsection, subsectionDto.getMeasuringTools());
        }
        if (subsection.getCertificateEmployee() == null && subsectionDto.getEmployeeId() != null) {
            setCertificateEmployee(subsection, subsectionDto.getEmployeeId());
        }

        if (subsectionDto.getAutoDataCollection() != null) {
           setAutoDataCollection(subsection, subsectionDto.getAutoDataCollection());
        }
        if (flag) {
            subsection = repository.save(subsection);
            switch (type) {
                case SECTION -> sectionService.addSubsection(id, subsection);
                case PROTOCOL -> protocolService.addSubsection(id, subsection);
                case PROTOCOL_REPORT -> protocolReportService.addSubsection(id, subsection);
            }
        }
        return mapper.mapToSubsectionTemplateDto(subsection);
    }

    @Override
    public SubsectionTemplateDto update(UpdateSubsectionTemplateDto subsectionDto) {
        SubsectionTemplate subsection = getById(subsectionDto.getId());
        if (subsection != null) {
            subsection = mapper.mapToUpdateSubsectionTemplate(subsection
                                                            , subsectionDto.getSequentialNumber()
                                                            , subsectionDto.getSubsectionName()
                                                            , subsectionDto.isSequentialNumberVisible());
            if (subsectionDto.getDivision() != null) {
                setDivisionData(subsection, subsectionDto.getDivision());
            }
            if (subsectionDto.getDocumentation() != null) {
                setDocumentationData(subsection, subsectionDto.getDocumentation());
            }
            if (subsectionDto.getMeasuringTools() != null) {
                setMeasuringToolData(subsection, subsectionDto.getMeasuringTools());
            }
            if (subsectionDto.getEmployeeId() != null) {
                setCertificateEmployee(subsection, subsectionDto.getEmployeeId());
            }
            if (subsectionDto.getAutoDataCollection() != null) {
                setAutoDataCollection(subsection, subsectionDto.getAutoDataCollection());
            }
            return mapper.mapToSubsectionTemplateDto(repository.save(subsection));
        }
        throw new NotFoundException(
                String.format("Subsection template with id=%s not found for update", subsectionDto.getId())
        );
    }

    @Override
    public SubsectionTemplateDto get(Long id) {
        return mapper.mapToSubsectionTemplateDto(getById(id));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("SubsectionTemplate with id=%s not found for delete", id));
    }

    @Override
    public void addTable(Long id, TableTemplate table) {
        SubsectionTemplate template = getById(id);
        if (template.getTable() == null) {
            template.setTable(table);
            repository.save(template);
        }
    }

    private void setDivisionData(SubsectionTemplate subsection, DivisionDataDto division) {
        if (division != null) {
            subsection.setDivisionData(service.getDivisionData((division)));
        }
    }

    private void setDocumentationData(SubsectionTemplate subsection, DocumentationDataDto documentation) {
        if (subsection.getDocumentation() == null) {
            subsection.setDocumentation(new ArrayList<>());
        }
        subsection.getDocumentation().addAll(service.getDocumentationData((documentation)));
    }

    private void setMeasuringToolData(SubsectionTemplate subsection, List<MeasuringToolDataDto> measuringTools) {
        if (subsection.getMeasuringTools() == null) {
            subsection.setMeasuringTools(new ArrayList<>());
        }
        subsection.getMeasuringTools().addAll(service.getMeasuringToolData((measuringTools)));
    }

    private void setCertificateEmployee(SubsectionTemplate subsection, Long employeeId) {
        if (employeeId <= 0) {
            throw  new BadRequestException(String.format("Employee id=%s can only be positive", employeeId));
        }
        subsection.setCertificateEmployee(service.getCertificateData(employeeId));
    }

    private void setAutoDataCollection(SubsectionTemplate subsection, AutoDataCollectionDto autoDataCollection) {
        if (subsection.getAutoDataCollection().getId() < 1) {
            subsection.setAutoDataCollection(autoDataCollectionService.save(autoDataCollection));
            return;
        }
        subsection.setAutoDataCollection(
                autoDataCollectionService.update(subsection.getAutoDataCollection(), autoDataCollection));
    }


    public SubsectionTemplate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Subsection template with id=%s not found", id))
                );
    }

    private SubsectionTemplate exists(DataType type, Long id, String subsectionName) {
        Set<SubsectionTemplate> subsections = new HashSet<>();
        switch (type) {
            case SECTION -> subsections = repository.findBySection(id);
            case PROTOCOL -> subsections = repository.findByProtocol(id);
            case PROTOCOL_REPORT -> subsections = repository.findByProtocolReport(id);
        }
        if (subsections.isEmpty()) {
            return null;
        }
        return subsections.stream()
                .collect(Collectors.toMap(SubsectionTemplate::getSubsectionName, s -> s))
                .get(subsectionName);
    }
}