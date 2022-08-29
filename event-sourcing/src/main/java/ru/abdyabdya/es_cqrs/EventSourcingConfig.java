package ru.abdyabdya.es_cqrs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EventSourcingConfig {
    @Bean("applierContainerMap")
    public Map<Class, ApplierContainer> applierContainerMap(){
        return new HashMap<>();
    }
}
