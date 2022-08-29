package ru.abdyabdya.es_cqrs.event;

import lombok.experimental.SuperBuilder;
import ru.abdyabdya.es_cqrs.Command;

import java.util.UUID;

@SuperBuilder
public abstract class Event implements Command {
    private UUID id;
    private Long eventId;
}
