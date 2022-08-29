package ru.abdyabdya.es_cqrs.aspect;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.abdyabdya.es_cqrs.Command;
import ru.abdyabdya.es_cqrs.errors.CommandException;
import ru.abdyabdya.es_cqrs.Publisher;

import java.util.List;

@Aspect
@Component
public class HandlerContextAspect {

    @Autowired
    private Publisher publisher;

    @Pointcut(value = "@annotation(ru.abdyabdya.es_cqrs.annotations.Handler)")
    public void callAtHandlerAnnotation() { }

    @Around(value = "callAtHandlerAnnotation()")
    @SneakyThrows
    public Object aroundCallAt(ProceedingJoinPoint pjp) {
        List<Command> retVal = null;
        try {
            retVal = (List<Command>)pjp.proceed();
            publisher.publish(retVal);
        } catch (CommandException commandException) {
            publisher.fail(commandException.getCommand());
        }
        return retVal;
    }
}
