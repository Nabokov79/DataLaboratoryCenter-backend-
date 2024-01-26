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
@Table(name = "geodesic_rejection_parameters")
public class GeodesicRejectionParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_object")
    private Boolean full;
    @Column(name = "max_precipitation")
    private Integer maxPrecipitation;
    @Column(name = "max_neighboring_points")
    private Integer maxDifferenceNeighboringPoints;
    @Column(name = "max_diametric_point")
    private Integer maxDifferenceDiametricPoint;
    @Column(name = "measurement_error")
    private Float measurementError;
}