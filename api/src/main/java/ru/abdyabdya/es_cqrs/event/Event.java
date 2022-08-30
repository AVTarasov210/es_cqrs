package ru.abdyabdya.es_cqrs.event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.abdyabdya.es_cqrs.Command;

import java.util.UUID;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class Event implements Command {
    private UUID id;
    private Long eventId;
}
