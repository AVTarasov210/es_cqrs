package ru.abdyabdya.es_cqrs.bpp;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.abdyabdya.es_cqrs.ApplierFunction;
import ru.abdyabdya.es_cqrs.ApplierContainer;
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


    private final Map<Class, ApplierContainer> appliers;
    private final Map<String, Class> appliersNames;

    public CommandApplierBeanPostProcessor(@Qualifier("applierContainerMap") Map<Class, ApplierContainer> appliers) {
        this.appliers = appliers;
        appliersNames = new HashMap<>();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplierContainer) {
            appliersNames.put(beanName, ((ApplierContainer<?>) bean).getApplyingClass());
            List <Method> applierMethods = Stream.of(bean.getClass().getMethods())
                    .filter(x -> x.isAnnotationPresent(Applier.class))
                    .collect(Collectors.toList());
            if (!applierMethods.isEmpty()) {
                for (Method method : applierMethods) {
                    ((ApplierContainer<?>) bean).add(
                            method.getParameterTypes()[1],
                            getApplierFunction(bean, method));
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (appliersNames.containsKey(beanName)){
            appliers.put(appliersNames.get(beanName), (ApplierContainer) bean);
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @SneakyThrows
    private ApplierFunction getApplierFunction(@NonNull Object object,
                                               @NonNull Method applyMethod) {
        return (obj, command) -> {
            try {
                return applyMethod.invoke(object, obj, command);
            } catch (IllegalAccessException|InvocationTargetException  e) {
                throw new RuntimeException(e);
            }
        };
    }


}
