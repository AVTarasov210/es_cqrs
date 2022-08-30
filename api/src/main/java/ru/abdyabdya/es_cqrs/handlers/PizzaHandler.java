package ru.abdyabdya.es_cqrs.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.abdyabdya.es_cqrs.Command;
import ru.abdyabdya.es_cqrs.annotations.Handler;
import ru.abdyabdya.es_cqrs.event.BuyPizza;
import ru.abdyabdya.es_cqrs.event.SendPizzaNotification;

import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static java.util.List.of;

@Component
@Slf4j
public class PizzaHandler {

    @Handler
    public List<Command> handle(BuyPizza command){
        if (command.getUsername()!=null && command.getUsername().isBlank())
            throw new RuntimeException("имя должно быть заполнено");
        if (command.getCountOfPieces() != null && command.getCountOfPieces() > 0)
            throw new RuntimeException("кусочков не может не быть");
        if (command.getCountOfPieces() != null && command.getPrice() > 0)
            throw new RuntimeException("пицца обязательно чего-то стоит");
        log.debug(format("Мистер %s купил %s кусков пиццы за %s денег",
                command.getUsername(),
                command.getCountOfPieces(),
                command.getPrice()));
        return of(SendPizzaNotification.builder().username(command.getUsername()).build());
    }
    @Handler
    public List<Command> handle(SendPizzaNotification command) {
        log.debug(format("Пицца пицца у %s пицца!!!", command.getUsername()));
        return Collections.emptyList();
    }
}
