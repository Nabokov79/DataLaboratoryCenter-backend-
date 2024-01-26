package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.rejection.surveyObject.GeodesicRejectionParametersDto;
import ru.nabokovsg.data.dto.rejection.surveyObject.NewGeodesicRejectionParametersDto;
import ru.nabokovsg.data.dto.rejection.surveyObject.UpdateGeodesicRejectionParametersDto;
import ru.nabokovsg.data.models.GeodesicRejectionParameters;

@Mapper(componentModel = "spring")
public interface GeodesicRejectionParametersMapper {

    GeodesicRejectionParameters mapToNewGeodesicRejectionParameters(
                                                              NewGeodesicRejectionParametersDto rejectionParametersDto);

    GeodesicRejectionParameters mapToUpdateGeodesicRejectionParameters(
                                                           UpdateGeodesicRejectionParametersDto rejectionParametersDto);

    GeodesicRejectionParametersDto mapToGeodesicRejectionParametersDto(GeodesicRejectionParameters rejectionParameters);
}