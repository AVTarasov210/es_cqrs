package ru.abdyabdya.es_cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements ApplierEventService {

    @Override
    public List<? extends Command> findAllByEventId(ApplyingObject object) {
        return Collections.emptyList();
    }
}
