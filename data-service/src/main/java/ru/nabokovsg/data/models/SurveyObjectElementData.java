package ru.nabokovsg.data.models;

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
@Table(name = "survey_objects_elements")
public class SurveyObjectElementData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "element_id", referencedColumnName = "id")
    private Element element;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_element_id", referencedColumnName = "id")
    private SubElement subElement;
    @Column(name = "thickness")
    private Float thickness;
    @Column(name = "pipe_diameter_min")
    private Integer pipeDiameterMin;
    @Column(name = "pipe_wall_thickness_min")
    private Float pipeWallThicknessMin;
    @Column(name = "pipe_diameter_max")
    private Integer pipeDiameterMax;
    @Column(name = "pipe_wall_thickness_max")
    private Float pipeWallThicknessMax;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rejection_parameter_id", referencedColumnName = "id")
    private ElementRejectionParameters rejectionParameters;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_object_id",  nullable = false)
    private SurveyObject objectSurvey;
}