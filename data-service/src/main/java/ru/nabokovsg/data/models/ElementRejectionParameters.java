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
@Table(name = "element_rejection_parameters")
public class ElementRejectionParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "min")
    private Float min;
    @Column(name = "min_in_percent")
    private Float minInPercent;
    @Column(name = "min_hardness")
    private Float minHardness;
    @Column(name = "max_hardness")
    private Float maxHardness;
    @Column(name = "measurement_error")
    private Float measurementError;
}