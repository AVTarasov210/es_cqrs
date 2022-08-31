package ru.abdyabdya.es_cqrs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.abdyabdya.es_cqrs.event.BuyPizza;
import ru.abdyabdya.es_cqrs.event.TakePizzaPiece;

@SpringBootTest
class EsCqrsApplicationTests {

    @Autowired
    private Publisher publisher;

    @Test
    void contextLoads() {
        publisher.publish(BuyPizza.builder()
                .eventId(1l)
                .price(100)
                .countOfPieces(1)
                .username("john").build());
        publisher.publish(TakePizzaPiece.builder().eventId(1l).username("jack").build());
        publisher.publish(TakePizzaPiece.builder().eventId(1l).username("jack").build());
    }

}
