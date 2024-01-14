package ru.nabokovsg.data.dto.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class ApplicationSearchParametersDto {

    private Long addressId;
    private LocalDate startDatePeriod;
    private LocalDate endDatePeriod;
    private Long surveyObjectId;
    private Long objectTypeId;
    private Long employeeId;
    private Integer documentNumber;
    private String applicationStatus;
    private String documentStatus;
}