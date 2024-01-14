package ru.nabokovsg.templates.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "protocol_templates")
public class ProtocolTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "reporting_document_id")
    private Long reportingDocumentId;
    @Column(name = "object_type_id")
    private Long objectTypeId;
    @Column(name = "object_type")
    private String objectType;
    @Column(name = "title")
    private String title;
    @Column(name = "heading")
    private String heading;
    @OneToOne
    @JoinColumn(name = "header_id", referencedColumnName = "id")
    private HeaderTemplate header;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_characteristics_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "characteristic_id")})
    @ToString.Exclude
    private List<CharacteristicsSurveyObject> characteristics;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_subsection_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsection_id")})
    @ToString.Exclude
    private List<SubsectionTemplate> subsections;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_table_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "table_id")})
    @ToString.Exclude
    private List<TableTemplate> tables;
    @OneToOne
    @JoinColumn(name = "conclusion_template_id", referencedColumnName = "id")
    private ConclusionTemplate conclusions;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_recommendation_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "recommendation_id")})
    @ToString.Exclude
    private List<RecommendationTemplate> recommendations;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_appendices_templates",
            joinColumns = {@JoinColumn(name = "protocol_id")},
            inverseJoinColumns = {@JoinColumn(name = "appendices_id")})
    @ToString.Exclude
    private List<AppendicesTemplate> appendices;
}