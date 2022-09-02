package ru.abdyabdya.es_cqrs.dto;

import lombok.Data;
import ru.abdyabdya.es_cqrs.ApplyingObject;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Data
public class PizzaDto implements ApplyingObject<Long> {
    private Long id;
    private Instant lastDate;
    private String username;
    private Integer price = 0;
    private Integer piecePrice = 0;
    private Integer pieceCount = 0;
    private Map<String, Integer> borrowers = new HashMap<>();
}
