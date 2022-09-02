package ru.abdyabdya.es_cqrs;

import java.time.Instant;

public interface ApplyingObject<I> {
    I getId();
    Instant getLastDate();
}
