package ru.nabokovsg.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "measuring_tools")
public class MeasuringTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "toll")
    private String toll;
    @Column(name = "model")
    private String model;
    @Column(name = "work_number")
    private String workNumber;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "manufacturing")
    private LocalDate manufacturing;
    @Column(name = "exploitation")
    private LocalDate exploitation;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    private Organization manufacturer;
    @Column(name = "measuring_range")
    private String  measuringRange;
    @Column(name = "characteristics")
    private String characteristics;
    @ManyToOne
    @JoinColumn(name = "tool_owner_id", referencedColumnName = "id")
    private Organization toolOwner;
    @Column(name = "verification_date")
    private LocalDate verificationDate;
    @Column(name = "next_verification_date")
    private LocalDate nextVerificationDate;
    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;
    @Column(name = "certificate_number")
    private String certificateNumber;
    @Column(name = "registry")
    private String registry;
    @Column(name = "note")
    private String note;
    @Column(name = "control_type")
    private String controlType;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",  nullable = false)
    private Employee employee;
}