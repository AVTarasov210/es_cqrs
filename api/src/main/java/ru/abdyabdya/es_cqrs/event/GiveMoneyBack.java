package ru.abdyabdya.es_cqrs.event;

import lombok.Data;

@Data
public class GiveMoneyBack extends Event {
    private Integer amountOfMoney;
    private String username;
    private String targetUser;
}
