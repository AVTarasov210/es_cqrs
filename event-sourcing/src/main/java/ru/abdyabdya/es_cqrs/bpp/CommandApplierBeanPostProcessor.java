package ru.abdyabdya.es_cqrs.bpp;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.abdyabdya.es_cqrs.ApplierFunction;
import ru.abdyabdya.es_cqrs.ApplierContainer;
import ru.abdyabdya.es_cqrs.Command;
import ru.abdyabdya.es_cqrs.annotations.Applier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class CommandApplierBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, List<Method>> appliersNames = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplierContainer) {
            List<Method> applierMethods = Stream.of(bean.getClass().getMethods())
                    .filter(x -> x.isAnnotationPresent(Applier.class))
                    .collect(Collectors.toList());
            appliersNames.put(beanName, applierMethods);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (appliersNames.containsKey(beanName)) {
            if (!appliersNames.get(beanName).isEmpty()) {
                for (Method method : appliersNames.get(beanName)) {
                    ((ApplierContainer<?>) bean).add(
                            method.getParameterTypes()[1],
                            getApplierFunction(bean, method));
                }
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    private ApplierFunction getApplierFunction(@NonNull Object object, @NonNull Method applyMethod) {
        return (obj, command) -> invokeApplierFunction(object, applyMethod, obj, command);
    }

    @SneakyThrows
    private Object invokeApplierFunction(
            Object bean,
            Method applyMethod,
            Object processingObject,
            Command command) {
        return applyMethod.invoke(bean, processingObject, command);
    }

}
