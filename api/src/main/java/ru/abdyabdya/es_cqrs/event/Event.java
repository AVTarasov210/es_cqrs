package ru.abdyabdya.es_cqrs.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.abdyabdya.es_cqrs.Command;

import java.time.Instant;
import java.util.UUID;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Event implements Command {
    private UUID id;
    private Long eventId;
    @Builder.Default
    private Instant createAt = Instant.now();
}
