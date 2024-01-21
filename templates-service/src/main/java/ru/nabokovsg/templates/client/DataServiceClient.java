package ru.nabokovsg.templates.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nabokovsg.templates.dto.clientDto.*;

import java.util.Objects;

@Component
@AllArgsConstructor
public class DataServiceClient {

    private final WebClient webClient;

    public OrganizationDto getOrganization(final String uri) {
        return Objects.requireNonNull(webClient.get()
                        .uri(uri)
                        .retrieve()
                        .toEntity(OrganizationDto.class)
                        .block())
                .getBody();
    }

    public BranchDto getBranch(final String uri) {
        return Objects.requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(BranchDto.class)
                .block())
                .getBody();
    }

    public DepartmentDto getDepartment(final String uri) {
        return Objects.requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(DepartmentDto.class)
                .block())
                .getBody();
    }

    public ReportingDocumentDto getReportingDocument(final String uri) {
        return  Objects.requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(ReportingDocumentDto.class)
                .block())
                .getBody();
    }

    public ObjectsTypeDto getObjectsType(final String uri) {
        return  Objects.requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(ObjectsTypeDto.class)
                .block())
                .getBody();
    }

    public ShortEmployeeDto getShortEmployee(final String uri) {
        return  Objects.requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(ShortEmployeeDto.class)
                .block())
                .getBody();
    }

    public EmployeeDto getEmployee(final String uri) {
        return  Objects.requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(EmployeeDto.class)
                .block())
                .getBody();
    }
}