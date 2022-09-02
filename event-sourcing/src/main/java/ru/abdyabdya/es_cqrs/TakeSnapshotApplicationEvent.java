package ru.abdyabdya.es_cqrs;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class TakeSnapshotApplicationEvent extends ApplicationEvent {
    @Getter
    private final Long id;

    public TakeSnapshotApplicationEvent(Object source, Long id) {
        super(source);
        this.id = id;
    }
}
