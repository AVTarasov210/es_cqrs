package ru.abdyabdya.es_cqrs;

public interface CommandProcessor {
    Command processCommand(Command command);
    Command processFailure(Command command);
}
