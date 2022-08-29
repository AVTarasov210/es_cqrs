package ru.abdyabdya.es_cqrs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandProcessorImpl implements CommandProcessor {

    @Override
    public Command processCommand(Command command) {
        log.info("обрабатываем команду");
        return command;
    }

    @Override
    public Command processFailure(Command command) {
        log.info("обрабатываем ошибку");
        return command;
    }
}
