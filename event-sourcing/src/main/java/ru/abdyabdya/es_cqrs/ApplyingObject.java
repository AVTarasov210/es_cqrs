package ru.abdyabdya.es_cqrs;

import java.time.Instant;

public interface ApplyingObject {
    Long getEventId();
    Instant getLastDate();
}
