package ru.abdyabdya.es_cqrs.handlers;

import org.springframework.stereotype.Component;
import ru.abdyabdya.es_cqrs.Command;
import ru.abdyabdya.es_cqrs.annotations.Handler;
import ru.abdyabdya.es_cqrs.event.BuyPizza;

import java.util.Collections;
import java.util.List;

@Component
public class PizzaHandler {

    @Handler
    public List<Command> handle(BuyPizza command){

        return Collections.emptyList();
    }
}
