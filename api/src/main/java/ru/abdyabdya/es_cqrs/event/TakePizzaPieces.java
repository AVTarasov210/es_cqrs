package ru.abdyabdya.es_cqrs.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakePizzaPieces extends Event {
    private Long countOfPieces;
    private Long price;
    private String name;
}
