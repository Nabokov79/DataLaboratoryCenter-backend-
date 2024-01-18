package ru.nabokovsg.templates.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "characteristics_templates")
public class CharacteristicsSurveyObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_number")
    private String sequentialNumber;
    @Column(name = "characteristic_name")
    private String characteristicName;
    @Column(name = "sequential_number_visible")
    private boolean sequentialNumberVisible;
    @Column(name = "object_type_id")
    private Long objectTypeId;
    @Column(name = "use_in_report")
    private boolean useInReport;
    @Column(name = "use_in_protocol")
    private boolean useInProtocol;
}