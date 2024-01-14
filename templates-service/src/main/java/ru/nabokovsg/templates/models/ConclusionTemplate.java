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
@Table(name = "conclusion_templates")
public class ConclusionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "header_text")
    private String headerText;
    @Column(name = "if_than_norm")
    private String ifThanNorm;
    @Column(name = "approaching")
    private String approaching;
    @Column(name = "equal")
    private String equal;
    @Column(name = "if_less_norm")
    private String ifLessNorm;
    @Column(name = "if_no_norm")
    private String ifNoNorm;
}