package ru.abdyabdya.es_cqrs.applyers;

import ru.abdyabdya.es_cqrs.ApplierContainer;
import ru.abdyabdya.es_cqrs.annotations.Applier;
import ru.abdyabdya.es_cqrs.event.BuyPizza;
import ru.abdyabdya.es_cqrs.event.PieceTaken;

@Applier
public class PizzaPieceApplier extends ApplierContainer<Integer> {

    @Applier
    public Integer apply(Integer countOfPieces, BuyPizza command){
        if (countOfPieces==null) countOfPieces = 0;
        return countOfPieces + command.getCountOfPieces();
    }

    @Applier
    public Integer apply(Integer countOfPieces, PieceTaken command){
        return countOfPieces - 1;
    }

    @Override
    public Class getApplyingClass() {
        return Long.class;
    }
}
