package ru.nabokovsg.templates.models.enums;

import java.util.Optional;

public enum DivisionType {

    ORGANIZATION,
    BRANCH,
    DEPARTMENT;

    public static Optional<DivisionType> from(String divisionType) {
        for (DivisionType type : values()) {
            if (type.name().equalsIgnoreCase(divisionType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
