package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.surveyObjectElementData.rejection.ElementRejectionParametersDto;
import ru.nabokovsg.data.dto.surveyObjectElementData.rejection.NewElementRejectionParametersDto;
import ru.nabokovsg.data.dto.surveyObjectElementData.rejection.UpdateElementRejectionParametersDto;
import ru.nabokovsg.data.exceptions.BadRequestException;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.ElementRejectionParametersMapper;
import ru.nabokovsg.data.models.ElementRejectionParameters;
import ru.nabokovsg.data.repository.ElementRejectionParametersRepository;

@Service
@RequiredArgsConstructor
public class ElementRejectionParametersServiceImpl implements ElementRejectionParametersService {

    private final ElementRejectionParametersRepository repository;
    private final ElementRejectionParametersMapper mapper;
    private final ObjectsSurveyElementDataService objectsSurveyElementDataService;

    @Override
    public ElementRejectionParametersDto save(NewElementRejectionParametersDto rejectionParametersDto) {
        ElementRejectionParameters rejectionParameters = null;
        if (rejectionParametersDto.getSubElementId() == null) {
            rejectionParameters = repository.findByElementId(rejectionParametersDto.getElementId());
        }
        if (rejectionParametersDto.getSubElementId() != null) {
            if (rejectionParametersDto.getSubElementId() <=0) {
                throw  new BadRequestException(String.format("subElement id can only be positive: %s"
                                                            , rejectionParametersDto.getSubElementId()));
            }
            rejectionParameters = repository.findByElementIdAndSubElementId(rejectionParametersDto.getElementId()
                                                                          , rejectionParametersDto.getSubElementId());
            objectsSurveyElementDataService.addElementRejectionParameters(rejectionParametersDto.getElementId()
                                                                        , rejectionParameters);
        }
        if (rejectionParameters == null) {
            rejectionParameters = repository.save(mapper.maToNewElementRejectionParameters(rejectionParametersDto));
        }
        return mapper.maToElementRejectionParametersDto(rejectionParameters);
    }

    @Override
    public ElementRejectionParametersDto update(UpdateElementRejectionParametersDto rejectionParametersDto) {
        if (repository.existsById(rejectionParametersDto.getId())) {
            return mapper.maToElementRejectionParametersDto(
                                repository.save(mapper.maToUpdateElementRejectionParameters(rejectionParametersDto)));
        }
        throw new NotFoundException(String.format(
                        "ElementRejectionParameters with id=%s not found for update", rejectionParametersDto.getId()));
    }

    @Override
    public ElementRejectionParametersDto get(Long id) {
        return mapper.maToElementRejectionParametersDto(
                repository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                String.format("ElementRejectionParameters with id=%s not found", id))));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
           repository.deleteById(id);
           return;
        }
        throw new NotFoundException(String.format("ElementRejectionParameters with id=%s not found for delete", id));
    }
}