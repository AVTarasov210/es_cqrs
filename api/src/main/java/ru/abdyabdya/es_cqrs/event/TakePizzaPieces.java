package ru.abdyabdya.es_cqrs.event;

import lombok.Data;

@Data
public class TakePizzaPieces  extends Event {
    private Long countOfPieces;
    private Long price;
    private String name;
}
