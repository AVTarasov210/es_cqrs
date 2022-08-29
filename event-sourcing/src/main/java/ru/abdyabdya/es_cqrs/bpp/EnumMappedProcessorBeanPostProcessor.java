package ru.abdyabdya.es_cqrs.bpp;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.abdyabdya.es_cqrs.EnumMappedProcessorService;
import ru.abdyabdya.es_cqrs.annotations.OnValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class EnumMappedProcessorBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof EnumMappedProcessorService) {
            List <Method> applierMethods = Stream.of(bean.getClass().getMethods())
                    .filter(x -> x.isAnnotationPresent(OnValue.class))
                    .collect(Collectors.toList());
            if (!applierMethods.isEmpty()) {
                for (Method method : applierMethods) {
                    ((EnumMappedProcessorService<?>) bean).add(
                            method.getAnnotation(OnValue.class).value(),
                            getEnumMappedProcessorConsumer(bean, method));
                }
            }
        }
        return bean;
    }

    @SneakyThrows
    private BiConsumer getEnumMappedProcessorConsumer(@NonNull Object object, @NonNull Method method) {
        return (toUpdate, updateData) -> {
            try {
                method.invoke(object, toUpdate, updateData);
            } catch (IllegalAccessException|InvocationTargetException  e) {
                throw new RuntimeException(e);
            }
        };
    }


}
