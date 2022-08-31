package ru.abdyabdya.es_cqrs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.abdyabdya.es_cqrs.CommandRepository;
import ru.abdyabdya.es_cqrs.applyers.PizzaApplier;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final PizzaApplier pizzaApplier;
    private final CommandRepository commandRepository;

    public boolean checkPieces(Long id){
        return pizzaApplier.apply(commandRepository.findById(id)) > 0;
    }
}
