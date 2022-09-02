package ru.abdyabdya.es_cqrs.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.abdyabdya.es_cqrs.Command;
import ru.abdyabdya.es_cqrs.annotations.Handler;
import ru.abdyabdya.es_cqrs.event.*;
import ru.abdyabdya.es_cqrs.service.PizzaService;

import java.util.Collections;
import java.util.List;

import static java.util.List.of;

@Handler
@Slf4j
@RequiredArgsConstructor
public class PizzaHandler {

    private final PizzaService pizzaService;
    @Handler
    public List<Command> handle(BuyPizza command){
        if (command.getUsername()!=null && command.getUsername().isBlank())
            throw new RuntimeException("имя должно быть заполнено");
        if (command.getCountOfPieces() == null || command.getCountOfPieces() <= 0)
            throw new RuntimeException("кусочков не может не быть");
        if (command.getPrice() == null || command.getPrice() <= 0)
            throw new RuntimeException("пицца обязательно чего-то стоит");
        log.info("Мистер {} купил {} кусков пиццы за {} денег",
                command.getUsername(),
                command.getCountOfPieces(),
                command.getPrice());
        return of(SendPizzaNotification.builder()
                .username(command.getUsername())
                .build());
    }
    @Handler
    public List<Command> handle(SendPizzaNotification command) {
        log.info("Пицца пицца у {} пицца!!!", command.getUsername());
        return Collections.emptyList();
    }
    @Handler
    public List<Command> handle(TakePizzaPiece command) {
        if (pizzaService.checkPieces(command.getEventId())) {
            return of(PieceTaken.builder().eventId(command.getEventId()).username(command.getUsername()).build());
        } else {
            log.info("а кусочек взять нельзя");
            return null;
        }
    }

    @Handler
    public List<Command> handle(GiveMoneyBack command) {
        log.info("{} вернул {}", command.getUsername(), command.getAmountOfMoney());
        return Collections.emptyList();
    }

    @Handler
    public List<Command> handle(PieceTaken command) {
        log.info("{} взял кусочек", command.getUsername());
        return Collections.emptyList();
    }
}
