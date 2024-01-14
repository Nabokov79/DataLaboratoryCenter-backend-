package ru.nabokovsg.data.dto.measuringTool;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class RequestParameters {

    private String toll;
    private String model;
    private String workNumber;
    private LocalDate manufacturing;
    private LocalDate exploitation;
    private Long manufacturerId;
    private Long organizationId;
    private String controlType;
    private Long employeeId;
}