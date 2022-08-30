package ru.abdyabdya.es_cqrs.bpp;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.MethodInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.abdyabdya.es_cqrs.annotations.Handler;
import ru.abdyabdya.es_cqrs.Publisher;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;



@Component
@RequiredArgsConstructor
public class CommandHandlerBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, List<Method>> handlers = new HashMap<>();
    private final Publisher publisher;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        List<Method> handleMethods = Stream.of(bean.getClass().getMethods())
                .filter(x -> x.isAnnotationPresent(Handler.class))
                .collect(Collectors.toList());
        if (!handleMethods.isEmpty()){
            handlers.put(beanName, handleMethods);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (handlers.containsKey(beanName)) {
            handlers.get(beanName).forEach(x -> addWithInvoke(bean, x));
        }
        return bean;
    }

    private void addWithInvoke(@NonNull Object object, @NonNull Method handleMethod) {
        Class[] classes = handleMethod.getParameterTypes();
        if (classes.length != 1) throw new RuntimeException("handler mast contains in params only Command");
        publisher.addCommandHandler(classes[0], a -> invoke(object, handleMethod, a));
    }

    @SneakyThrows
    private void invoke(@NonNull Object object, @NonNull Method handleMethod, Object param)
    {
        handleMethod.invoke(object, param);
    }

}
