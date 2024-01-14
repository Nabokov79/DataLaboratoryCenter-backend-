package ru.nabokovsg.data.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.data.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.data.dto.measuringTool.RequestParameters;
import ru.nabokovsg.data.dto.measuringTool.UpdateMeasuringToolDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.MeasuringToolMapper;
import ru.nabokovsg.data.models.DataBuilder;
import ru.nabokovsg.data.models.MeasuringTool;
import ru.nabokovsg.data.models.QMeasuringTool;
import ru.nabokovsg.data.models.enums.BuilderType;
import ru.nabokovsg.data.repository.MeasuringToolRepository;
import ru.nabokovsg.data.services.builder.DataFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasuringToolServiceImpl implements MeasuringToolService {

    private final MeasuringToolRepository repository;
    private final MeasuringToolMapper mapper;
    private final EntityManager entityManager;
    private final DataFactory factory;

    @Override
    public List<MeasuringToolDto> save(List<NewMeasuringToolDto> measuringToolsDto) {
        DataBuilder builder = factory.getBuilder(measuringToolsDto.stream()
                        .map(mapper::mapFromNewMeasuringToolDto)
                        .distinct()
                        .toList()
                , BuilderType.MEASURING_TOOL);
        List<MeasuringTool> measuringTools = new ArrayList<>();
        for (NewMeasuringToolDto measuringToolDto : measuringToolsDto) {
            measuringTools.add(set(mapper.mapToNewMeasuringTool(measuringToolDto)
                                 , mapper.mapFromNewMeasuringToolDto(measuringToolDto)
                                 , builder));
        }
        return getListDto(repository.saveAll(measuringTools));
    }

    @Override
    public List<MeasuringToolDto> update(List<UpdateMeasuringToolDto> measuringToolsDto) {
        validateIds(measuringToolsDto.stream().map(UpdateMeasuringToolDto::getId).toList());
        DataBuilder builder = factory.getBuilder(measuringToolsDto.stream()
                        .map(mapper::mapFromUpdateMeasuringToolDto)
                        .distinct()
                        .toList()
                , BuilderType.MEASURING_TOOL);
        List<MeasuringTool> measuringTools = new ArrayList<>();
        for (UpdateMeasuringToolDto measuringToolDto : measuringToolsDto) {
            measuringTools.add(set(mapper.mapToUpdateMeasuringTool(measuringToolDto)
                    , mapper.mapFromUpdateMeasuringToolDto(measuringToolDto)
                    , builder));
        }
        return getListDto(repository.saveAll(measuringTools));
    }

    @Override
    public List<MeasuringToolDto> getAll(RequestParameters parameters) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (parameters.getToll() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.toll.eq(parameters.getToll()));
        }
        if (parameters.getModel() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.model.eq(parameters.getModel()));
        }
        if (parameters.getWorkNumber() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.workNumber.eq(parameters.getWorkNumber()));
        }
        if (parameters.getManufacturing() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.manufacturing.after(parameters.getManufacturing()));
        }
        if (parameters.getExploitation() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.exploitation.eq(parameters.getManufacturing()));
        }
        if (parameters.getManufacturerId() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.manufacturer.id.eq(parameters.getManufacturerId()));
        }
        if (parameters.getOrganizationId() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.organization.id.eq(parameters.getOrganizationId()));
        }
        if (parameters.getControlType() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.controlType.eq(parameters.getControlType()));
        }
        if (parameters.getEmployeeId() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.employee.id.eq(parameters.getEmployeeId()));
        }
        QMeasuringTool measuringTool = QMeasuringTool.measuringTool;
        return getListDto(new JPAQueryFactory(entityManager).from(measuringTool)
                                                            .select(measuringTool)
                                                            .where(booleanBuilder)
                                                            .fetch());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("measuring tool with id = %s not found for delete", id));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, MeasuringTool> measuringTools = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(MeasuringTool::getId, m -> m));
        if (measuringTools.size() != ids.size() || measuringTools.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(measuringTools.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("measuring tools with ids= %s not found", ids));
        }
    }

    private MeasuringTool set(MeasuringTool measuringTool, ObjectsIds ids, DataBuilder builder) {
        measuringTool.setOrganization(builder.getOrganizations().get(ids.getOrganizationId()));
        measuringTool.setToolOwner(builder.getOrganizations().get(ids.getToolOwnerId()));
        measuringTool.setManufacturer(builder.getOrganizations().get(ids.getManufacturerId()));
        if (ids.getEmployeeId() != null) {
            measuringTool.setEmployee(builder.getEmployees().get(ids.getEmployeeId()));
        }
        return measuringTool;
    }

    private List<MeasuringToolDto> getListDto(List<MeasuringTool> measuringTools) {
        return measuringTools.stream()
                             .map(mapper::mapToMeasuringToolDto)
                             .toList();
    }
}