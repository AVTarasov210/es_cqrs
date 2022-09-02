package ru.abdyabdya.es_cqrs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.abdyabdya.es_cqrs.event.BuyPizza;
import ru.abdyabdya.es_cqrs.event.TakePizzaPiece;
import ru.abdyabdya.es_cqrs.repository.PizzaRepository;
import ru.abdyabdya.es_cqrs.service.PizzaService;

@SpringBootTest
class EsCqrsApplicationTests {

    @Autowired
    private Publisher publisher;
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private SnapshotService snapshotService;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Test
    void contextLoads() {
        publisher.publish(BuyPizza.builder()
                .eventId(1l)
                .price(100)
                .countOfPieces(2)
                .username("john").build());
        publisher.publish(TakePizzaPiece.builder().eventId(1l).username("jack").build());

        System.out.println(pizzaRepository.findById(1L));
        snapshotService.takeSnapshot(1l);
        System.out.println(pizzaRepository.findById(1L));

        publisher.publish(TakePizzaPiece.builder().eventId(1l).username("jim").build());
        publisher.publish(TakePizzaPiece.builder().eventId(1l).username("jack").build());
        System.out.println(pizzaService.getById(1l));
    }

}
