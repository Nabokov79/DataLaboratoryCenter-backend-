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
@Table(name = "licenses")
public class Licenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "license_number")
    private String licenseNumber;
    @Column(name = "start_data")
    private LocalDate startData;
    @Column(name = "end_data")
    private LocalDate endData;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "issued_license_id", referencedColumnName = "id")
    private Organization issuedLicense;
}