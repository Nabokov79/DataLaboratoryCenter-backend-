package ru.nabokovsg.data.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.ObjectsIds;
import ru.nabokovsg.data.dto.certificate.CertificateDto;
import ru.nabokovsg.data.dto.certificate.NewCertificateDto;
import ru.nabokovsg.data.dto.certificate.UpdateCertificateDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.CertificateMapper;
import ru.nabokovsg.data.models.Certificate;
import ru.nabokovsg.data.models.DataBuilder;
import ru.nabokovsg.data.models.QCertificate;
import ru.nabokovsg.data.models.enums.BuilderType;
import ru.nabokovsg.data.repository.CertificateRepository;
import ru.nabokovsg.data.services.builder.DataFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository repository;

    private final CertificateMapper mapper;
    private final EntityManager entityManager;
    private final DataFactory factory;

    @Override
    public List<CertificateDto> save(List<NewCertificateDto> certificatesDto) {
        DataBuilder builder = factory.getBuilder(certificatesDto.stream()
                                                                .map(mapper::mapFromNewCertificateDto)
                                                                .toList()
                                                        , BuilderType.CERTIFICATE);
        List<Certificate> certificates = new ArrayList<>();
        for (NewCertificateDto certificateDto : certificatesDto) {
            certificates.add(set(mapper.mapToNewCertificate(certificateDto)
                               , mapper.mapFromNewCertificateDto(certificateDto)
                               , builder));
        }
        return getListDto(repository.saveAll(certificates));
    }

    @Override
    public List<CertificateDto> update(List<UpdateCertificateDto> certificatesDto) {
        validateIds(certificatesDto.stream().map(UpdateCertificateDto::getId).toList());
        DataBuilder builder = factory.getBuilder(certificatesDto.stream()
                                                                .map(mapper::mapFromUpdateCertificateDto)
                                                                .toList()
                                                        , BuilderType.CERTIFICATE);
        List<Certificate> certificates = new ArrayList<>();
        for (UpdateCertificateDto certificateDto : certificatesDto) {
            certificates.add(set(mapper.mapToUpdateCertificate(certificateDto)
                    , mapper.mapFromUpdateCertificateDto(certificateDto)
                    , builder));
        }
        return getListDto(repository.saveAll(certificates));
    }

    @Override
    public List<CertificateDto> getAll(Long employeeId, LocalDate date) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (employeeId != null) {
            booleanBuilder.and(QCertificate.certificate.employee.id.eq(employeeId));
        }
        if (date != null) {
            booleanBuilder.and(QCertificate.certificate.endDate.before(date));
        }
        QCertificate certificates = QCertificate.certificate;
        JPAQueryFactory qf = new JPAQueryFactory(entityManager);
        JPAQuery<Certificate> query = qf.from(certificates)
                .select(certificates)
                .where(booleanBuilder);
        return getListDto(query.fetch());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("certificate with id = %s not found for delete", id));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Certificate> certificates = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Certificate::getId, subheading -> subheading));
        if (certificates.size() != ids.size() || certificates.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(certificates.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("certificates with ids= %s not found", ids));
        }
    }

    private Certificate set(Certificate certificate, ObjectsIds ids, DataBuilder builder) {
        certificate.setOrganization(builder.getOrganizations().get(ids.getOrganizationId()));
        certificate.setEmployee(builder.getEmployees().get(ids.getEmployeeId()));
        return certificate;
    }

    private List<CertificateDto> getListDto(List<Certificate> certificates) {
        return certificates.stream().map(mapper::mapToCertificatesDto).toList();
    }
}