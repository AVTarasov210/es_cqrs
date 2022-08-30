package ru.abdyabdya.es_cqrs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.abdyabdya.es_cqrs.event.Event;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandProcessorImpl implements CommandProcessor {

    List<Command> commandList = new LinkedList<>();

    @Override
    public Command processCommand(Command command) {
        if (command instanceof Event) {
            commandList.add(command);
        }
        log.info("обрабатываем команду");
        return command;
    }

    @Override
    public Command processFailure(Command command) {
        log.info("обрабатываем ошибку");
        return command;
    }
}
