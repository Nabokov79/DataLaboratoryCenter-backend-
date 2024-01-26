package ru.nabokovsg.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "survey_objects")
public class SurveyObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "object_type_id", referencedColumnName = "id")
    private ObjectsType objectType;
    @Column(name = "stationary_number")
    private Integer stationaryNumber;
    @Column(name = "volume")
    private Integer volume;
    @OneToMany(mappedBy = "objectSurvey", fetch = FetchType.LAZY)
    private List<SurveyObjectElementData> elements;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "survey_objects_rejection_parameters",
            joinColumns = {@JoinColumn(name = "survey_object_id")},
            inverseJoinColumns = {@JoinColumn(name = "rejection_parameter_id")})
    @ToString.Exclude
    private Set<GeodesicRejectionParameters> rejectionParameters;
    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;
}