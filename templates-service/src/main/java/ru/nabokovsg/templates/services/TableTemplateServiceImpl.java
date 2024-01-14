package ru.nabokovsg.templates.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.table.NewTableTemplateDto;
import ru.nabokovsg.templates.dto.table.TableTemplateDto;
import ru.nabokovsg.templates.dto.table.UpdateTableTemplateDto;
import ru.nabokovsg.templates.exceptions.NotFoundException;
import ru.nabokovsg.templates.mappers.TableTemplateMapper;
import ru.nabokovsg.templates.models.TableTemplate;
import ru.nabokovsg.templates.models.enums.DataType;
import ru.nabokovsg.templates.repository.TableTemplateRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableTemplateServiceImpl implements TableTemplateService {

    private final TableTemplateRepository repository;
    private final TableTemplateMapper mapper;
    private final ColumnHeaderTemplateService columnHeaderService;
    private final SubsectionTemplateService subsectionService;
    private final ProtocolTemplateService protocolService;
    private final ProtocolReportTemplateService protocolReportService;

    @Override
    public TableTemplateDto save(DataType type, Long id, NewTableTemplateDto tableDto) {
        TableTemplate table = exists(type, id, tableDto.getTableName(), tableDto.getSequentialNumber());
        if (table == null) {
             table = repository.save(mapper.mapToNewTableTemplate(tableDto
                    , columnHeaderService.save(tableDto.getColumnHeaders())));
            switch (type) {
                case SUBSECTION -> subsectionService.addTable(id, table);
                case PROTOCOL -> protocolService.addTable(id, table);
                case PROTOCOL_REPORT -> protocolReportService.addTable(id, table);
            }
        }
        return mapper.mapToTableTemplateDto(table);
    }

    @Override
    public TableTemplateDto update(UpdateTableTemplateDto tableDto) {
        if (repository.existsById(tableDto.getId())) {
            return mapper.mapToTableTemplateDto(repository.save(mapper.mapToUpdateTableTemplate(
                                                                tableDto
                                                              , columnHeaderService.update(tableDto.getColumnHeaders()))
                    )
            );
        }
       throw new NotFoundException(String.format("Table template with id=%s not found for update", tableDto.getId()));
    }

    @Override
    public TableTemplateDto get(Long id) {
        return mapper.mapToTableTemplateDto(getById(id));
    }

    public TableTemplate getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Table template with id=%s not found", id)));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw  new NotFoundException(String.format("Table template with id=%s not found for delete", id));
    }

    private TableTemplate exists(DataType type, Long id, String tableName, Integer sequentialNumber) {
        TableTemplate table = null;
        switch (type) {
            case SUBSECTION -> table = repository.findBySubsection(id);
            case PROTOCOL ->  table = repository.findByProtocol(id)
                                                        .stream()
                                                        .collect(Collectors.toMap(TableTemplate::getTableName, t -> t))
                                                        .get(tableName);
            case PROTOCOL_REPORT -> { table = repository.findByProtocolReport(id)
                                                        .stream()
                                                        .collect(Collectors.toMap(TableTemplate::getTableName, t -> t))
                                                        .get(tableName);
               if (table != null && sequentialNumber != null && !table.getSequentialNumber().equals(sequentialNumber)) {
                   table = null;
               }
            }
        }
        return table;
    }
}