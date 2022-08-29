package ru.abdyabdya.es_cqrs;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@RequiredArgsConstructor
public abstract class EnumMappedProcessorService<T> {

    protected Map <String, BiConsumer<T, Object>> enumMappedProcessorsMap = new HashMap <>();

    public BiConsumer<T,Object> get(String enumValue){
        return enumMappedProcessorsMap.get(enumValue);
    }

    public void add(String enumValue, BiConsumer<T, Object> applierFunction){
        enumMappedProcessorsMap.put(enumValue, applierFunction);
    }
}
