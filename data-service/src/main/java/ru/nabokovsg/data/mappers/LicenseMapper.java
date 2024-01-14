package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.license.LicenseDto;
import ru.nabokovsg.data.dto.license.NewLicenseDto;
import ru.nabokovsg.data.dto.license.UpdateLicenseDto;
import ru.nabokovsg.data.models.Licenses;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LicenseMapper {

    Licenses mapToNewLicense(NewLicenseDto licenseDto);

    LicenseDto mapToLicenseDto(Licenses license);

    Licenses mapToUpdateLicense(UpdateLicenseDto licenseDto);
}