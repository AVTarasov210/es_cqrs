package ru.abdyabdya.es_cqrs.repository;

import org.springframework.stereotype.Repository;
import ru.abdyabdya.es_cqrs.dto.PizzaDto;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PizzaRepository {
    private final List<PizzaDto> pizzaDtos = new ArrayList<>();

    public PizzaDto findById(Long id){
        return pizzaDtos.stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(PizzaDto dto){
        pizzaDtos.add(dto);
    }
}
