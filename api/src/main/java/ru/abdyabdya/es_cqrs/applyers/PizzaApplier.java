package ru.abdyabdya.es_cqrs.applyers;

import ru.abdyabdya.es_cqrs.ApplierContainer;
import ru.abdyabdya.es_cqrs.annotations.Applier;
import ru.abdyabdya.es_cqrs.event.BuyPizza;
import ru.abdyabdya.es_cqrs.event.PieceTaken;
import ru.abdyabdya.es_cqrs.event.TakePizzaPiece;

@Applier
public class PizzaApplier extends ApplierContainer<Long> {

    @Applier
    public Long apply(Long countOfPieces, BuyPizza command){
        if (countOfPieces==null) countOfPieces = 0l;
        return countOfPieces + command.getCountOfPieces();
    }

    @Applier
    public Long apply(Long countOfPieces, PieceTaken command){
        return countOfPieces - 1;
    }

    @Override
    public Class getApplyingClass() {
        return Long.class;
    }
}
