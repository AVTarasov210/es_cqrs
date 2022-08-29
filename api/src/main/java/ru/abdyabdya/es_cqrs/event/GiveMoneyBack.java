package ru.abdyabdya.es_cqrs.event;

import lombok.Data;

@Data
public class GiveMoneyBack extends Event {
    private Long amountOfMoney;
    private String name;
}
