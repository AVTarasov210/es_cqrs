package ru.abdyabdya.es_cqrs.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PizzaDto {
    private String username;
    private Integer price = 0;
    private Integer piecePrice = 0;
    private Integer pieceCount = 0;
    private Map<String, Integer> borrowers = new HashMap<>();
}
