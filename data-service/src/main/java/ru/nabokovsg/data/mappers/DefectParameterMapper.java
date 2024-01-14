package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.sizeParameters.NewSizeParametersDto;
import ru.nabokovsg.data.dto.sizeParameters.UpdateSizeParametersDto;
import ru.nabokovsg.data.models.SizeParameters;

@Mapper(componentModel = "spring")
public interface DefectParameterMapper {

    SizeParameters mapToNewDefectParameter(NewSizeParametersDto parameterDto);

    SizeParameters mapToUpdateDefectParameter(UpdateSizeParametersDto parameterDto);
}