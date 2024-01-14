package ru.nabokovsg.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ObjectsIds {

    private Long objectTypeId;
    private Long buildingId;
    private Long departmentId;
    private Long addressId;
    private Long organizationId;
    private Long employeeId;
    private Long toolOwnerId;
    private Long ownerId;
    private Long manufacturerId;
    private Long branchId;
    private Long issuedLicenseId;
    private Long surveyObjectId;
    private Long workPerformedId;
    private Long reportingDocumentId;
}