package ru.nabokovsg.templates.dto.passportData;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PredicateData {

    private Long objectTypeId;
    private boolean useInReport;
    private boolean useInProtocolSurvey;
    private boolean useInProtocolQuality;

    @Override
    public String toString() {
        return "PredicateData{" +
                ", useInReport=" + useInReport +
                ", useInProtocolSurvey=" + useInProtocolSurvey +
                ", useInProtocolQuality=" + useInProtocolQuality +
                '}';
    }
}