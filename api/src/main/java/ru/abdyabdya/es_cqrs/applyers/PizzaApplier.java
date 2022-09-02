package ru.abdyabdya.es_cqrs.applyers;

import ru.abdyabdya.es_cqrs.ApplierContainer;
import ru.abdyabdya.es_cqrs.annotations.Applier;
import ru.abdyabdya.es_cqrs.dto.PizzaDto;
import ru.abdyabdya.es_cqrs.event.BuyPizza;
import ru.abdyabdya.es_cqrs.event.PieceTaken;

@Applier
public class PizzaApplier extends ApplierContainer<PizzaDto> {

    @Applier
    public PizzaDto apply(PizzaDto pizzaDto, BuyPizza command){
        if (pizzaDto==null) pizzaDto = new PizzaDto();
        if (pizzaDto.getUsername()!=null && !pizzaDto.getUsername().equals(command.getUsername())){
            throw new RuntimeException("разные люди не могут покупать пиццу с одинаковыми айди");
        }
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
        return pizzaDto;
    }

    @Override
    public Class getApplyingClass() {
        return Long.class;
    }
}
