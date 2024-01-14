package ru.nabokovsg.data.models;

import jakarta.persistence.*;
import lombok.*;
import ru.nabokovsg.data.models.enums.Status;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;
    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToOne
    @JoinColumn(name = "survey_object_id", referencedColumnName = "id")
    private SurveyObject surveyObject;
    @Column(name = "work_performed")
    private String workPerformed;
    @OneToOne
    @JoinColumn(name = "reporting_document_id", referencedColumnName = "id")
    private ReportingDocument reportingDocument;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "applications_employees",
            joinColumns =  {@JoinColumn(name = "application_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    @ToString.Exclude
    private List<Employee> employees;
    @Column(name = "task_source")
    private String taskSource;
    @Column(name = "need_drawing")
    private Boolean needDrawing;
    @Column(name = "comment")
    private String comment;
    @Column(name = "application_status")
    @Enumerated(EnumType.STRING)
    private Status applicationStatus;
}