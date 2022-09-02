package ru.abdyabdya.es_cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SnapshotServiceImpl implements SnapshotService {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void takeSnapshot(Long id) {
        applicationEventPublisher.publishEvent(new TakeSnapshotApplicationEvent(this, id));
    }
}
