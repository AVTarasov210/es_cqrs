package ru.abdyabdya.es_cqrs.applyers;

import lombok.RequiredArgsConstructor;

import ru.abdyabdya.es_cqrs.SnapshotApplierContainer;
import ru.abdyabdya.es_cqrs.annotations.Applier;
import ru.abdyabdya.es_cqrs.dto.PizzaDto;
import ru.abdyabdya.es_cqrs.event.BuyPizza;
import ru.abdyabdya.es_cqrs.event.PieceTaken;
import ru.abdyabdya.es_cqrs.repository.PizzaRepository;

@Applier
@RequiredArgsConstructor
public class PizzaApplier extends SnapshotApplierContainer<PizzaDto, Long> {

    private final PizzaRepository pizzaRepository;

    @Applier
    public PizzaDto apply(PizzaDto pizzaDto, BuyPizza command){
        if (pizzaDto==null) pizzaDto = new PizzaDto();
        if (pizzaDto.getUsername()!=null && !pizzaDto.getUsername().equals(command.getUsername())){
            throw new RuntimeException("разные люди не могут покупать пиццу с одинаковыми айди");
        }
        pizzaDto.setId(command.getEventId());
        pizzaDto.setLastDate(command.getCreateAt());
        pizzaDto.setUsername(command.getUsername());
        pizzaDto.setPiecePrice(command.getPrice()/command.getCountOfPieces());
        pizzaDto.setPrice(pizzaDto.getPrice()+command.getPrice());
        pizzaDto.setPieceCount(pizzaDto.getPieceCount()+command.getCountOfPieces());

        return pizzaDto;
    }

    @Applier
    public PizzaDto apply(PizzaDto pizzaDto, PieceTaken command){
        if (pizzaDto.getBorrowers().containsKey(command.getUsername())){
            pizzaDto.getBorrowers().put(
                    command.getUsername(),
                    pizzaDto.getBorrowers().get(command.getUsername())+pizzaDto.getPiecePrice());
        } else {
            pizzaDto.getBorrowers().put(command.getUsername(), pizzaDto.getPiecePrice());
        }
        pizzaDto.setLastDate(command.getCreateAt());
        return pizzaDto;
    }

    @Override
    public Class getApplyingClass() {
        return Long.class;
    }

    @Override
    protected PizzaDto getApplyingObjectById(Long id) {
        return pizzaRepository.findById(id);
    }

    @Override
    protected void saveApplyingObject(PizzaDto applyingObject) {
        pizzaRepository.save(applyingObject);
    }
}
