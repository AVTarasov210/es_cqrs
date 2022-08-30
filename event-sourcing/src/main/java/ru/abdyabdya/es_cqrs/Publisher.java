package ru.abdyabdya.es_cqrs;

import java.util.List;
import java.util.function.Consumer;

public interface Publisher {
    void publish(Command message);
    void publish(List<Command> proceed);
    void fail(Command message);
    void fail(List<Command> proceed);
    void addCommandHandler(Class type, Consumer<Command> consumer);
}
