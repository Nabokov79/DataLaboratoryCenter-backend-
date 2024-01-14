package ru.nabokovsg.templates.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subsection_templates")
public class SubsectionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_number")
    private Double sequentialNumber;
    @Column(name = "subsection_name")
    private String subsectionName;
    @Column(name = "user_text")
    private String userText;
    @Column(name = "sequential_number_visible")
    private boolean sequentialNumberVisible;
    @Column(name = "certificate_employee")
    private String certificateEmployee;
    @Column(name = "division_data")
    private String divisionData;
    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private TableTemplate table;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subsection_documentation_templates",
            joinColumns = {@JoinColumn(name = "subsection_id")},
            inverseJoinColumns = {@JoinColumn(name = "documentation_id")})
    @ToString.Exclude
    private List<DocumentationTemplate> documentation;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subsection_measuring_tools_templates",
            joinColumns = {@JoinColumn(name = "subsection_id")},
            inverseJoinColumns = {@JoinColumn(name = "tool_id")})
    @ToString.Exclude
    private List<MeasuringToolTemplate> measuringTools;
    @OneToOne
    @JoinColumn(name = "auto_data_id", referencedColumnName = "id")
    private AutoDataCollection autoDataCollection;
}