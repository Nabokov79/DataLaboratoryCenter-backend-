package ru.nabokovsg.templates.services;

import ru.nabokovsg.templates.dto.protocolReport.NewProtocolReportTemplateDto;
import ru.nabokovsg.templates.dto.protocolReport.ProtocolReportTemplateDto;
import ru.nabokovsg.templates.dto.protocolReport.ShortProtocolReportTemplateDto;
import ru.nabokovsg.templates.dto.protocolReport.UpdateProtocolReportTemplateDto;
import ru.nabokovsg.templates.models.ConclusionTemplate;
import ru.nabokovsg.templates.models.SubsectionTemplate;
import ru.nabokovsg.templates.models.TableTemplate;

import java.util.List;

public interface ProtocolReportTemplateService {

    ShortProtocolReportTemplateDto save(Long sectionId, NewProtocolReportTemplateDto protocolDto);

    ShortProtocolReportTemplateDto update(UpdateProtocolReportTemplateDto protocolDto);

    ProtocolReportTemplateDto get(Long id);

    List<ShortProtocolReportTemplateDto> getAll();

    void addTable(Long id, TableTemplate table);

    void addSubsection(Long id, SubsectionTemplate subsection);

    void addConclusion(Long id, ConclusionTemplate conclusion);
}