package ru.abdyabdya.es_cqrs.event;

import lombok.Builder;
import lombok.Data;
import ru.abdyabdya.es_cqrs.Command;

@Data
@Builder
public class SendPizzaNotification implements Command {
    private String username;
}
