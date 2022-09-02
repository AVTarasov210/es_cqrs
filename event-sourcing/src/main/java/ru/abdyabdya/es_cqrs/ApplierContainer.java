package ru.abdyabdya.es_cqrs;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ApplierContainer<T> {

    private Map <Class, ApplierFunction<T>> classConsumerMap = new HashMap <>();

    public T apply(T object, List <? extends Command> commands){
        for (Command command: commands) {
            if (classConsumerMap.containsKey(command.getClass())) {
                object = classConsumerMap.get(command.getClass()).apply(object, command);
            }
        }
        return object;
    }

    public T apply(List <? extends Command> commands){
        return apply(null, commands);
    }

    public void add(Class cls, ApplierFunction<T> applierFunction){
        classConsumerMap.put(cls, applierFunction);
    }

    public abstract Class<T> getApplyingClass();
}
