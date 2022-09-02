package ru.abdyabdya.es_cqrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class SnapshotApplierContainer<T extends ApplyingObject, I> extends ApplierContainer<T> implements ApplicationListener<TakeSnapshotApplicationEvent> {

    @Autowired
    private CommandService<I> commandService;

    protected abstract T getApplyingObjectById(I id);

    protected abstract void saveApplyingObject(T applyingObject);

    public T apply(I id) {
        T applyingObject = getApplyingObjectById(id);
        List<? extends Command> commands = applyingObject == null ?
                commandService.getCommandsById(id) :
                commandService.getCommandsByIdAndDate(id, applyingObject.getLastDate());

        if (!commands.isEmpty()) {
            applyingObject = apply(applyingObject, commands);
            saveApplyingObject(applyingObject);
            return applyingObject;
        } else {
            return applyingObject;
        }
    }

    @Override
    public void onApplicationEvent(TakeSnapshotApplicationEvent event) {
        apply((I) event.getId());
    }
}
