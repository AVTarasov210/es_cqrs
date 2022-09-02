package ru.abdyabdya.es_cqrs;

import org.springframework.stereotype.Repository;
import ru.abdyabdya.es_cqrs.event.Event;

import java.time.Instant;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    List<Event> eventList = new LinkedList<>();

    public void save(Event command){
        eventList.add(command);
    }

    public List<Event> findById(Long id){
        return eventList.stream()
                .filter(x->x.getEventId().equals(id))
                .sorted(Comparator.comparing(Event::getCreateAt))
                .collect(Collectors.toList());
    }

    public List<Event> findById(Long id, Instant createAtAfter){
        return eventList.stream()
                .filter(x->x.getEventId().equals(id))
                .filter(x->x.getCreateAt().isAfter(createAtAfter))
                .sorted(Comparator.comparing(Event::getCreateAt))
                .collect(Collectors.toList());
    }
}
