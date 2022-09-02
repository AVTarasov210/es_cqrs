package ru.abdyabdya.es_cqrs;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandServiceImpl implements CommandService<Long> {

    private final EventRepository eventRepository;

    @Override
    public List<? extends Command> getCommandsByIdAndDate(Long id, Instant lastAppliedEventCreateDate) {
        return eventRepository.findById(id, lastAppliedEventCreateDate);
    }

    @Override
    public List<? extends Command> getCommandsById(Long id) {
        return eventRepository.findById(id);
    }
}
