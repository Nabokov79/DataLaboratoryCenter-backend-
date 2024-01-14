package ru.nabokovsg.templates.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "section_templates")
public class SectionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "section_name")
    private String sectionName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_characteristics_templates",
            joinColumns = {@JoinColumn(name = "section_id")},
            inverseJoinColumns = {@JoinColumn(name = "characteristic_id")})
    @ToString.Exclude
    private List<CharacteristicsSurveyObject> characteristics;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_subsection_templates",
            joinColumns = {@JoinColumn(name = "section_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsection_id")})
    @ToString.Exclude
    private List<SubsectionTemplate> subsections;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_protocol_templates",
            joinColumns = {@JoinColumn(name = "section_id")},
            inverseJoinColumns = {@JoinColumn(name = "protocol_id")})
    @ToString.Exclude
    private List<ProtocolReportTemplate> protocols;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_recommendation_templates",
            joinColumns = {@JoinColumn(name = "section_id")},
            inverseJoinColumns = {@JoinColumn(name = "recommendation_id")})
    @ToString.Exclude
    private List<RecommendationTemplate> recommendations;

    @Override
    public String toString() {
        return "SectionTemplate{" +
                "id=" + id +
                ", sequentialNumber=" + sequentialNumber +
                ", sectionName='" + sectionName + '\'' +
                ", subsections=" + subsections +
                ", protocols=" + protocols +
                ", recommendations=" + recommendations +
                '}';
    }
}