package ru.abdyabdya.es_cqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class PublisherImpl implements Publisher {

    private final Map<Class<? extends Command>, Collection<Consumer<Command>>> consumerHandlerMap = new HashMap<>();

    private final CommandProcessor commandProcessor;

    @Override
    public Command publish(Command command) {
        Collection<Consumer<Command>> handlers = consumerHandlerMap.get(command.getClass());
        if (handlers != null && !handlers.isEmpty()){
            Command processedCommand = commandProcessor.processCommand(command);
            handlers.forEach(x -> x.accept(processedCommand));
            return processedCommand;
        } else {
            throw new IllegalStateException(format("No handlers exist for class %s", command.getClass()));
        }
    }

    @Override
    public void publish(List<Command> commands) {
        commands.forEach(this::publish);
    }

    @Override
    public void fail(Command command) {
        Collection<Consumer<Command>> handlers = consumerHandlerMap.get(command.getClass());
        if (handlers != null && !handlers.isEmpty()){
            Command processedCommand = commandProcessor.processFailure(command);
            handlers.forEach(x -> x.accept(processedCommand));
        } else {
            throw new IllegalStateException(format("No handlers exist for class %s", command.getClass()));
        }
    }

    @Override
    public void fail(List<Command> commands) {
        commands.forEach(this::fail);
    }

    @Override
    public void add(Class type, Consumer<Command> consumer) {
        if (consumerHandlerMap.containsKey(type)) {
            consumerHandlerMap.get(type).add(consumer);
        } else {
            List<Consumer<Command>> handlerList = new LinkedList();
            handlerList.add(consumer);
            consumerHandlerMap.put(type, handlerList);
        }
    }
}
