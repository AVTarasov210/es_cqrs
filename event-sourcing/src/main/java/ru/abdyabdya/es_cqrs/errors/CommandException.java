package ru.abdyabdya.es_cqrs.errors;

import lombok.Getter;
import ru.abdyabdya.es_cqrs.Command;

public class CommandException extends RuntimeException{
    @Getter
    private final Command command;

    public CommandException(Command command) {
        this.command = command;
    }

    public CommandException(Command command, Throwable cause) {
        super(cause);
        this.command = command;
    }

    public CommandException(Command command, String message) {
        super(message);
        this.command = command;
    }
}
