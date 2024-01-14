package ru.nabokovsg.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "defects")
public class Defect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "defect_name")
    private String defectName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "defects_size_parameters",
            joinColumns = {@JoinColumn(name = "defect_id")},
            inverseJoinColumns = {@JoinColumn(name = "size_parameter_id")})
    @ToString.Exclude
    private List<SizeParameters> parameters;
}