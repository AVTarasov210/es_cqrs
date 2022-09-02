package ru.abdyabdya.es_cqrs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.abdyabdya.es_cqrs.dto.PizzaDto;
import ru.abdyabdya.es_cqrs.event.BuyPizza;
import ru.abdyabdya.es_cqrs.event.TakePizzaPiece;
import ru.abdyabdya.es_cqrs.service.PizzaService;

@SpringBootTest
class EsCqrsApplicationTests {

    @Autowired
    private Publisher publisher;
    @Autowired
    private PizzaService pizzaService;

    @Test
    void contextLoads() {
        publisher.publish(BuyPizza.builder()
                .eventId(1l)
                .price(100)
                .countOfPieces(2)
                .username("john").build());
        System.out.println(pizzaService.getById(1l));
        publisher.publish(TakePizzaPiece.builder().eventId(1l).username("jack").build());
        System.out.println(pizzaService.getById(1l));
        publisher.publish(TakePizzaPiece.builder().eventId(1l).username("jack").build());
        System.out.println(pizzaService.getById(1l));
        publisher.publish(TakePizzaPiece.builder().eventId(1l).username("jack").build());
        System.out.println(pizzaService.getById(1l));
    }

}
