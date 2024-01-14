package ru.nabokovsg.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.data.models.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reporting_document_data")
public class ReportingDocumentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;
    @Column(name = "document_number")
    private Integer documentNumber;
    @Column(name = "document_start_date")
    private LocalDateTime documentStartDate;
    @Column(name = "document_end_date")
    private LocalDateTime documentEndDate;
    @Column(name = "transfer_date")
    private LocalDate transferDate;
    @Column(name = "next_survey_date")
    private LocalDate nextSurveyDate;
    @OneToOne
    @JoinColumn(name = "employee_document_id", referencedColumnName = "id")
    private Employee employeeDocument;
    @OneToOne
    @JoinColumn(name = "employee_drawing_id", referencedColumnName = "id")
    private Employee employeeDrawing;
    @Column(name = "document_status")
    @Enumerated(EnumType.STRING)
    private Status documentStatus;
    @Column(name = "drawing_status")
    @Enumerated(EnumType.STRING)
    private Status drawingStatus;
    @Column(name = "document_path")
    private String documentPath;
    @Column(name = "drawing_path")
    private String drawingPath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportingDocumentData that = (ReportingDocumentData) o;
        return id == that.id && Objects.equals(application, that.application)
                             && Objects.equals(documentNumber, that.documentNumber)
                             && Objects.equals(documentStartDate, that.documentStartDate)
                             && Objects.equals(documentEndDate, that.documentEndDate)
                             && Objects.equals(transferDate, that.transferDate)
                             && Objects.equals(nextSurveyDate, that.nextSurveyDate)
                             && Objects.equals(employeeDocument, that.employeeDocument)
                             && Objects.equals(employeeDrawing, that.employeeDrawing)
                             && documentStatus == that.documentStatus
                             && drawingStatus == that.drawingStatus
                             && Objects.equals(documentPath, that.documentPath)
                             && Objects.equals(drawingPath, that.drawingPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, application, documentNumber, documentStartDate, documentEndDate, transferDate
                          , nextSurveyDate, employeeDocument, employeeDrawing, documentStatus, drawingStatus
                          , documentPath, drawingPath);
    }
}