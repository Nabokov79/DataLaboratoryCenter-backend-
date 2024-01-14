package ru.nabokovsg.data.models.enums;

import java.util.Optional;

public enum Status {

    WAITING,
    WORK,
    COMPLETED,
    PRINT,
    PASSED,
    CHECK;

    public static Optional<Status> from(String stringStatus) {
        for (Status status : values()) {
            if (status.name().equalsIgnoreCase(stringStatus)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
