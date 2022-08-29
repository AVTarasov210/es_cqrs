package ru.abdyabdya.es_cqrs;

import java.util.List;

public interface ApplierEventService {
    List<? extends Command> findAllByEventId(ApplyingObject object);
}
