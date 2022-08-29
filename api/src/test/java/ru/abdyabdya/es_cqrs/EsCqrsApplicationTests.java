package ru.abdyabdya.es_cqrs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EsCqrsApplicationTests {

    @Autowired
    private Publisher publisher;

    @Test
    void contextLoads() {
    }

}
