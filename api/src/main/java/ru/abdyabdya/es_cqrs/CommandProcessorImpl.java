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

    private final CommandRepository commandRepository;

    @Override
    public Command processCommand(Command command) {
        if (command instanceof Event) {
            commandRepository.save((Event)command);
        }
        return command;
    }

    @Override
    public Command processFailure(Command command) {
        log.info("обрабатываем ошибку");
        return command;
    }
}
