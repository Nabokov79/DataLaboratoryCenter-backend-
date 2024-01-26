package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.surveyObjectElementData.rejection.ElementRejectionParametersDto;
import ru.nabokovsg.data.dto.surveyObjectElementData.rejection.NewElementRejectionParametersDto;
import ru.nabokovsg.data.dto.surveyObjectElementData.rejection.UpdateElementRejectionParametersDto;
import ru.nabokovsg.data.models.ElementRejectionParameters;

@Mapper(componentModel = "spring")
public interface ElementRejectionParametersMapper {

    ElementRejectionParameters maToNewElementRejectionParameters(
                                                           NewElementRejectionParametersDto rejectionParametersDto);

    ElementRejectionParameters maToUpdateElementRejectionParameters(
                                                        UpdateElementRejectionParametersDto rejectionParametersDto);

    ElementRejectionParametersDto maToElementRejectionParametersDto(ElementRejectionParameters rejectionParameters);
}