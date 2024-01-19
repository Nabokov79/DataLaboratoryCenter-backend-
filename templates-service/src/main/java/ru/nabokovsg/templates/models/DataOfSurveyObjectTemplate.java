package ru.nabokovsg.templates.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "data_object_templates")
public class DataOfSurveyObjectTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "object_type_id")
    private Long objectTypeId;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "characteristic_name")
    private String characteristicName;
    @Column(name = "sequential_number_visible")
    private boolean sequentialNumberVisible;
    @Column(name = "use_in_report")
    private boolean useInReport;
    @Column(name = "use_in_protocol_survey")
    private boolean useInProtocolSurvey;
    @Column(name = "use_in_protocol_quality")
    private boolean useInProtocolQuality;

    @Override
    public String toString() {
        return "DataOfSurveyObjectPassportTemplate{" +
                "id=" + id +
                ", objectTypeId=" + objectTypeId +
                ", sequentialNumber=" + sequentialNumber +
                ", characteristicName='" + characteristicName + '\'' +
                ", sequentialNumberVisible=" + sequentialNumberVisible +
                ", useInReport=" + useInReport +
                ", useInProtocolSurvey=" + useInProtocolSurvey +
                ", useInProtocolQuality=" + useInProtocolQuality +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataOfSurveyObjectTemplate that = (DataOfSurveyObjectTemplate) o;
        return id == that.id && sequentialNumberVisible == that.sequentialNumberVisible
                             && useInReport == that.useInReport
                             && useInProtocolSurvey == that.useInProtocolSurvey
                             && useInProtocolQuality == that.useInProtocolQuality
                             && Objects.equals(objectTypeId, that.objectTypeId)
                             && Objects.equals(sequentialNumber, that.sequentialNumber)
                             && Objects.equals(characteristicName, that.characteristicName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objectTypeId, sequentialNumber, characteristicName
                            , sequentialNumberVisible, useInReport, useInProtocolSurvey, useInProtocolQuality);
    }
}