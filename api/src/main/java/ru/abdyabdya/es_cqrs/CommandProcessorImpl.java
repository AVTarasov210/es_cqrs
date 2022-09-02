package ru.abdyabdya.es_cqrs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.abdyabdya.es_cqrs.event.Event;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandProcessorImpl implements CommandProcessor {

    private final EventRepository eventRepository;

    @Override
    public Command processCommand(Command command) {
        if (command instanceof Event) {
            eventRepository.save((Event)command);
        }
        return command;
    }

    @Override
    public Command processFailure(Command command) {
        log.info("обрабатываем ошибку");
        return command;
    }
}
