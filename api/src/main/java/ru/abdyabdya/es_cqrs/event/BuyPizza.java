package ru.abdyabdya.es_cqrs.event;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BuyPizza extends Event {
    private Integer countOfPieces;
    private Integer price;
    private String username;
}
