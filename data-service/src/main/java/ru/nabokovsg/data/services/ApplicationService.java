package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.application.ApplicationDto;
import ru.nabokovsg.data.dto.application.ApplicationSearchParametersDto;
import ru.nabokovsg.data.dto.application.NewApplicationDto;
import ru.nabokovsg.data.dto.application.UpdateApplicationDto;

import java.util.List;

public interface ApplicationService {

    List<ApplicationDto> save(List<NewApplicationDto> applicationsDto);

    List<ApplicationDto> update(List<UpdateApplicationDto> applicationsDto);

    ApplicationDto get(Long id);

   List<ApplicationDto> getAll(ApplicationSearchParametersDto parameters);
}