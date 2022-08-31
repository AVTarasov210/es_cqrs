package ru.abdyabdya.es_cqrs;

import org.springframework.stereotype.Repository;
import ru.abdyabdya.es_cqrs.event.Event;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CommandRepository {
    List<Event> commandList = new LinkedList<>();

    public void save(Event command){
        commandList.add(command);
    }

    public List<Event> findById(Long id){
        return commandList.stream()
                .filter(x->x.getEventId().equals(id))
                .sorted(Comparator.comparing(Event::getCreateAt))
                .collect(Collectors.toList());
    }
}
