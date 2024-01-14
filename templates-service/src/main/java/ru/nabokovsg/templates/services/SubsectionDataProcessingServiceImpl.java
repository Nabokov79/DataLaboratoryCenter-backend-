package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.client.TemplateClient;
import ru.nabokovsg.templates.dto.clientDto.DocumentationDto;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataParam;
import ru.nabokovsg.templates.dto.subsectionDada.DocumentationDataDto;
import ru.nabokovsg.templates.dto.subsectionDada.MeasuringToolDataDto;
import ru.nabokovsg.templates.exceptions.BadRequestException;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.SubsectionDataProcessingMapper;
import ru.nabokovsg.templates.models.DocumentationTemplate;
import ru.nabokovsg.templates.models.MeasuringToolTemplate;
import ru.nabokovsg.templates.models.enums.DivisionType;
import ru.nabokovsg.templates.services.builders.StringBuilderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubsectionDataProcessingServiceImpl implements SubsectionDataProcessingService {

    private final SubsectionDataProcessingMapper mapper;
    private final StringBuilderService stringBuilder;
    private final TemplateClient client;
    private final DocumentationDataService documentationDataService;
    private final MeasuringToolDataService toolDataService;

    @Override
    public List<DocumentationTemplate> getDocumentationData(DocumentationDataDto documentationDataDto) {
        List<DocumentationDto> documentations = client.getObjectsType(documentationDataDto.getObjectTypeId())
                                                      .getDocumentations();
        if (documentationDataDto.getMethodologicalDocument()) {
            documentations = documentations.stream()
                    .filter(d -> d.getMethodologicalDocument().equals(true))
                    .toList();
        }
        if (documentationDataDto.getRegulatoryDocument()) {
            documentations = documentations.stream()
                    .filter(d -> d.getRegulatoryDocument().equals(true))
                    .toList();
        }
        return documentationDataService.save(documentations.stream()
                                                           .map(stringBuilder::buildFromDocumentation)
                                                           .toList());
    }

    @Override
    public List<MeasuringToolTemplate> getMeasuringToolData(List<MeasuringToolDataDto> measuringToolDataDto) {
        return toolDataService.save(measuringToolDataDto);
    }

    @Override
    public String getDivisionData(DivisionDataDto divisionDataDto) {
        DivisionDataParam param = mapper.mapToDivisionDataParam(divisionDataDto);
        DivisionType type = DivisionType.from(divisionDataDto.getDivisionType())
                .orElseThrow(
                        () -> new BadRequestException(String.format("Unknown division type=%s"
                                                                   , divisionDataDto.getDivisionType()))
                );
        switch (type) {
            case ORGANIZATION ->
            {
                return stringBuilder.buildFromOrganization(
                        client.getOrganization(divisionDataDto.getDivisionId()), param);
            }
            case BRANCH ->
            {
                return stringBuilder.buildFromBranch(
                        client.getBranch(divisionDataDto.getDivisionId()), param);
            }
            case DEPARTMENT ->
            {
                return stringBuilder.buildFromDepartment(
                        client.getDepartment(divisionDataDto.getDivisionId()), param);
            }
            default -> throw new NotFoundException(
                    String.format("Division with type=%s, id=%s not found", divisionDataDto.getDivisionType()
                                                                          , divisionDataDto.getDivisionId()));
        }
    }

    @Override
    public String getCertificateData(Long employeeId) {
        return stringBuilder.buildFromEmployeeCertificate(client.getEmployee(employeeId));
    }
}
