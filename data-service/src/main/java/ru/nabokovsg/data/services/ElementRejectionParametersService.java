package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.surveyObjectElementData.rejection.ElementRejectionParametersDto;
import ru.nabokovsg.data.dto.surveyObjectElementData.rejection.NewElementRejectionParametersDto;
import ru.nabokovsg.data.dto.surveyObjectElementData.rejection.UpdateElementRejectionParametersDto;

public interface ElementRejectionParametersService {

    ElementRejectionParametersDto save(NewElementRejectionParametersDto rejectionParametersDto);

    ElementRejectionParametersDto update(UpdateElementRejectionParametersDto rejectionParametersDto);

    ElementRejectionParametersDto get(Long id);

    void delete(Long id);
}