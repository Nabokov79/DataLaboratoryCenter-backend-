package ru.nabokovsg.data.models.enums;

import java.util.Optional;

public enum DivisionType {

    ORGANIZATION,
    BRANCH,
    DEPARTMENT;

    public static Optional<DivisionType> from(String license) {
        for (DivisionType type : values()) {
            if (type.name().equalsIgnoreCase(license)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}