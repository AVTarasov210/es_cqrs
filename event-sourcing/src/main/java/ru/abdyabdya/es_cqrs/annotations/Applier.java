package ru.abdyabdya.es_cqrs.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Applier {
}
