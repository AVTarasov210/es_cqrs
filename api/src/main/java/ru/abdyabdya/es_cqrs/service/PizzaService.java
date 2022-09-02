package ru.abdyabdya.es_cqrs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.abdyabdya.es_cqrs.EventRepository;
import ru.abdyabdya.es_cqrs.applyers.PizzaApplier;
import ru.abdyabdya.es_cqrs.applyers.PizzaPieceApplier;
import ru.abdyabdya.es_cqrs.dto.PizzaDto;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final PizzaPieceApplier pizzaPieceApplier;
    private final PizzaApplier pizzaApplier;
    private final EventRepository eventRepository;

    public boolean checkPieces(Long id){
        return pizzaPieceApplier.apply(eventRepository.findById(id)) > 0;
    }

    public PizzaDto getById(Long id){
        return pizzaApplier.apply(eventRepository.findById(id));
    }
}
