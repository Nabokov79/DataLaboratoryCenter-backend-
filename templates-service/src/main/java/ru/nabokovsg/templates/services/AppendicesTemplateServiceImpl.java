package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.appendices.AppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.NewAppendicesTemplateDto;
import ru.nabokovsg.templates.dto.appendices.UpdateAppendicesTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.AppendicesTemplateMapper;
import ru.nabokovsg.templates.models.AppendicesTemplate;
import ru.nabokovsg.templates.models.enums.DataType;
import ru.nabokovsg.templates.repository.AppendicesTemplateRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppendicesTemplateServiceImpl implements AppendicesTemplateService {

    private final AppendicesTemplateRepository repository;
    private final AppendicesTemplateMapper mapper;
    private final ReportTemplateService reportService;
    private final ProtocolTemplateService protocolService;

    @Override
    public AppendicesTemplateDto save(DataType type, NewAppendicesTemplateDto appendicesDto) {
        AppendicesTemplate appendices = exists(type, appendicesDto);
        if (appendices == null) {
            appendices = repository.save(mapper.mapToNewAppendicesTemplate(appendicesDto));
            switch (type) {
                case REPORT -> reportService.addAppendices(appendicesDto.getId(), appendices);
                case PROTOCOL -> protocolService.addAppendices(appendicesDto.getId(), appendices);
            }
        }
        return mapper.mapToAppendicesTemplateDto(appendices);
    }

    @Override
    public AppendicesTemplateDto update(UpdateAppendicesTemplateDto appendicesDto) {
        if (repository.existsById(appendicesDto.getId())) {
            return mapper.mapToAppendicesTemplateDto(
                    repository.save(mapper.mapToUpdateAppendicesTemplate(appendicesDto))
            );
        }
        throw new NotFoundException(
                String.format("Appendices template with id=%s not found for update", appendicesDto.getId())
        );
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw  new NotFoundException(String.format("Appendices template with id=%s not found for delete", id));
    }


    public AppendicesTemplate exists(DataType type, NewAppendicesTemplateDto appendicesDto) {
        Set<AppendicesTemplate> appendices = null;
        String string = String.join("", appendicesDto.getAppendicesName()
                                              , String.valueOf(appendicesDto.getSequentialNumber()));
        switch (type) {
            case REPORT -> appendices = repository.findByReportId(appendicesDto.getId());
            case PROTOCOL -> appendices = repository.findByProtocolId(appendicesDto.getId());
        }
        if (appendices == null) {
            return null;
        }
        return appendices.stream()
                         .collect(Collectors.toMap(a -> String.join("", a.getAppendicesName()
                                , String.valueOf(a.getSequentialNumber())), a -> a))
                         .get(string);
    }
}