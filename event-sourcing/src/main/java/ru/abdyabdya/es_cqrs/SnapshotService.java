package ru.abdyabdya.es_cqrs;

import org.springframework.stereotype.Component;

@Component
public interface SnapshotService {

    void takeSnapshot(Long id);

}
