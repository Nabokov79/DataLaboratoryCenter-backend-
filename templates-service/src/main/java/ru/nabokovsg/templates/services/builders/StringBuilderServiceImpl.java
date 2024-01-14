package ru.nabokovsg.templates.services.builders;

import org.springframework.stereotype.Service;
import ru.nabokovsg.templates.dto.clientDto.*;
import ru.nabokovsg.templates.dto.subsectionDada.DivisionDataParam;
import ru.nabokovsg.templates.exceptions.BadRequestException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StringBuilderServiceImpl implements StringBuilderService {

    @Override
    public String buildFromDocumentation(DocumentationDto documentations) {
        String string = String.join("", "«", documentations.getTitle(), "»");
        if (documentations.getNumber() != null) {
            string = String.join(" ", documentations.getNumber(), string);
        }
        if (documentations.getView() != null) {
            string = String.join(" ", documentations.getView(), string);
        }
        return string;
    }

    @Override
    public String buildFromLicense(List<LicenseDto> licenses) {
        LicenseDto license = licenses.stream()
                .filter(l -> l.getEndData().isAfter(LocalDate.now()))
                .toList()
                .get(0);
        if (license == null) {
            license = licenses.stream()
                    .max(Comparator.comparing(LicenseDto::getEndData))
                    .orElseThrow(NoSuchElementException::new);
        }
        return String.join(" ", license.getDocumentType(),
                                                  "№",
                                                  license.getLicenseNumber(),
                                                  "от",
                                                  license.getStartData().toString());
    }

    @Override
    public String buildFromContacts(ContactDto contact) {
        return String.join(" "
                , String.join(" ","тел./факс", contact.getPhone(),"/", contact.getFax())
                , String.join(" ", "E-mail:", contact.getEmail()));
    }

    @Override
    public String buildFromAddress(AddressDto address) {
        String string = String.join(", ", address.getCity()
                                                , String.join(" ", address.getStreet()
                                                                     , "д.", String.valueOf(address.getHouseNumber())));
        if (address.getBuildingNumber() != null) {
            string = String.join(", ", string, String.join(""
                                                     , "корп.", String.valueOf(address.getBuildingNumber())));
        }
        if (address.getLetter() != null) {
            string = String.join(", ", string, String.join("", "лит.", address.getLetter()));
        }
        if (address.getIndex() != null) {
            return String.join(", ", String.valueOf(address.getIndex()), string);
        } else {
            return string;
        }
    }

    @Override
    public String buildFromShortEmployee(ShortEmployeeDto employee) {
        return String.join("/"
                , employee.getPost()
                , String.join(". ", String.join(".", String.valueOf(employee.getName().charAt(0))
                                                                   , String.valueOf(employee.getPatronymic().charAt(0)))
                                                                    .toUpperCase()
                                         , employee.getSurname()));
    }

    @Override
    public String buildFromEmployeeCertificate(EmployeeDto employee) {
        String employeeData = String.join(" - "
                , employee.getPost()
                , String.join(". ", String.join(" ", employee.getSurname()
                        , String.join(""
                                , String.valueOf(employee.getName().charAt(0)),"."
                                , String.valueOf(employee.getPatronymic().charAt(0)), ".,")
                                .toUpperCase())
                )
        );
        if (employee.getCertificate().isEmpty()) {
            throw new BadRequestException(String.format("- %s отсутствуют данные аттестации", employeeData));
        }
        List<String> documents = employee.getCertificate().stream()
                                                          .map(this::buildFromCertificate)
                                                          .distinct()
                                                          .toList();
        List<String> qualification = new ArrayList<>();
        if (documents.size() == 1) {
            List<String> levels = employee.getCertificate().stream().map(CertificateDto::getLevel).distinct().toList();
            for (String level : levels) {
                List<CertificateDto> certificates = employee.getCertificate()
                        .stream()
                        .filter(c -> c.getLevel().contains(level))
                        .toList();
                qualification.add(String.join(", ", buildByQualificationData(certificates, level)));
            }
        }
        return String.join(" ", employeeData, String.join(", "
                        , qualification.stream().sorted().toList())
                , String.join("", "(",documents.get(0), ")"));
    }

    public String buildByQualificationData(List<CertificateDto> certificate, String level) {
        return String.join(" "
               , level
               , "уровень квалификации по"
               , String.join(", ", certificate.stream().map(CertificateDto::getControlType).sorted().toList()));
    }

    public String buildFromCertificate(CertificateDto certificate) {
        String day = String.valueOf(certificate.getStartDate().getDayOfMonth());
        if (day.length() == 1) {
            day = String.join("", String.valueOf(0), day);
        }
        String month = String.valueOf(certificate.getStartDate().getMonthValue());
        if (month.length() == 1) {
            month = String.join("", String.valueOf(0), month);
        }
        String year = String.join(" ", String.valueOf(certificate.getStartDate().getYear()), "г.,");
        return String.join(" ", certificate.getDocumentType()
                                      , "№", certificate.getCertificateNumber()
                                      , "от", String.join(".", day , month, year)
                                      , "выдано", certificate.getOrganization().getShortNameOrganization());
    }

    @Override
    public String buildFromOrganization(OrganizationDto organization, DivisionDataParam param) {
        String divisionData = String.join(". "
                , getDivisionName(organization.getOrganization(), param.getDivisionName())
                , buildFromAddress(organization.getAddress()));
        if (param.getLicense()) {
            divisionData = String.join(". ", divisionData, buildFromLicense(organization.getLicenses()));
        }
        if (param.getLicense()) {
            divisionData = String.join(". ", divisionData, buildFromLicense(organization.getLicenses()));
        }
        return divisionData;
    }

    @Override
    public String buildFromBranch(BranchDto branch, DivisionDataParam param) {
        String divisionData = String.join(". "
                                                    , getDivisionName(branch.getBranch(), param.getDivisionName())
                                                    , buildFromAddress(branch.getAddress()));
        if (param.getLicense()) {
            divisionData = String.join(". ", divisionData, buildFromLicense(branch.getLicenses()));
        }
        return divisionData;
    }

    @Override
    public String buildFromDepartment(DepartmentDto department, DivisionDataParam param) {
        String divisionData = String.join(". "
                                                  , getDivisionName(department.getDepartment(), param.getDivisionName())
                                                  , buildFromAddress(department.getAddress()));
        if (param.getLicense()) {
            divisionData = String.join(". ", divisionData, buildFromLicense(department.getLicenses()));
        }
        return divisionData;
    }

    @Override
    public String buildFromObjectsType(ObjectsTypeDto objectType) {
        if (objectType.getObjectName() != null && objectType.getModel() != null && objectType.getOrientation() != null) {
            return String.join(" ", objectType.getObjectName(), objectType.getModel(), objectType.getOrientation());
        }
        if (objectType.getObjectName() != null && objectType.getModel() != null) {
            return String.join(" ", objectType.getObjectName(), objectType.getModel());
        }
        if (objectType.getObjectName() != null && objectType.getOrientation() != null) {
            return String.join(" ", objectType.getObjectName(), objectType.getOrientation());
        }
        return objectType.getObjectName();
    }

    private String getDivisionName(String name, String divisionName){
        if (divisionName != null) {
            return divisionName;
        }
        return name;
    }
}