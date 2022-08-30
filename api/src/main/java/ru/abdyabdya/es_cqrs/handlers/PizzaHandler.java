package ru.abdyabdya.es_cqrs.handlers;

import lombok.extern.slf4j.Slf4j;
import ru.abdyabdya.es_cqrs.Command;
import ru.abdyabdya.es_cqrs.annotations.Handler;
import ru.abdyabdya.es_cqrs.errors.CommandException;
import ru.abdyabdya.es_cqrs.event.BuyPizza;
import ru.abdyabdya.es_cqrs.event.GiveMoneyBack;
import ru.abdyabdya.es_cqrs.event.SendPizzaNotification;
import ru.abdyabdya.es_cqrs.event.TakePizzaPiece;

import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static java.util.List.of;

@Handler
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
    @Handler
    public List<Command> handle(TakePizzaPiece command) {
        if (canTakeOnePiece()) {
            log.debug(format("%s взял кусочек", command.getUsername()));
        } else {
            throw new IllegalArgumentException();
        }
        return Collections.emptyList();
    }

    private boolean canTakeOnePiece() {
        return true;
    }

    @Handler
    public List<Command> handle(GiveMoneyBack command) {
        log.debug(format("%s вернул %s", command.getUsername(), command.getAmountOfMoney()));
        return Collections.emptyList();
    }
}
