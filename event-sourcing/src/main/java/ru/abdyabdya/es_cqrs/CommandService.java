package ru.abdyabdya.es_cqrs;


import java.time.Instant;
import java.util.List;

public interface CommandService<I> {
    List<? extends Command> getCommandsByIdAndDate(I id, Instant lastAppliedEventCreateDate);
    List<? extends Command> getCommandsById(I id);
}
