package ru.abdyabdya.es_cqrs.aspect;

import lombok.SneakyThrows;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.abdyabdya.es_cqrs.ApplierContainer;
import ru.abdyabdya.es_cqrs.ApplierEventService;
import ru.abdyabdya.es_cqrs.ApplyingObject;

import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ApplierServiceAspect {

    private final Map<Class, ApplierContainer> appliers;
    private final ApplierEventService applierEventService;

    public ApplierServiceAspect(@Qualifier("applierContainerMap") Map<Class, ApplierContainer> appliers, ApplierEventService applierEventService) {
        this.appliers = appliers;
        this.applierEventService = applierEventService;
    }

    @Pointcut(value = "@annotation(ru.abdyabdya.es_cqrs.annotations.ApplierRepository) && execution(public * *(..))")
    public void callAtApplier() {
    }

    @AfterReturning(value = "callAtApplier()", returning = "retVal")
    @SneakyThrows
    public Object aroundCallAt(Object retVal) {
        if (retVal instanceof List){
            List<ApplyingObject> val = (List<ApplyingObject>) retVal;
            if (!val.isEmpty() && val.get(0) != null) {
                for (ApplyingObject o : val) {
                    appliers.get(val.get(0).getClass()).apply(o, applierEventService.findAllByEventId(o));
                }
            }
        } else if (retVal instanceof ApplyingObject) {
            return appliers.get(retVal.getClass()).apply(retVal,  applierEventService.findAllByEventId((ApplyingObject) retVal));
        }
        return retVal;
    }
}
