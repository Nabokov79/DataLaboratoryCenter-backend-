package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.license.LicenseDto;
import ru.nabokovsg.data.dto.license.NewLicenseDto;
import ru.nabokovsg.data.dto.license.UpdateLicenseDto;

import java.util.List;

public interface LicenseService {

    LicenseDto save(NewLicenseDto licenseDto);

    LicenseDto update(UpdateLicenseDto licenseDto);

    LicenseDto get(Long id);

    List<LicenseDto> getAll();
}