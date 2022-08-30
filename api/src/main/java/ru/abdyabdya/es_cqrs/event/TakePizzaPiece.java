package ru.abdyabdya.es_cqrs.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakePizzaPiece extends Event {
    private String username;
}
