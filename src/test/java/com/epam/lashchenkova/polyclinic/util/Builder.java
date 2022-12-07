package com.epam.lashchenkova.polyclinic.util;

import java.util.function.Consumer;

public class Builder<T> {
    private T instance;

    public static <T> Builder<T> build(Class<T> entityClass) {
        return new Builder<>(entityClass);
    }

    public Builder(Class<T> entityClass) {
        try {
            instance = entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(String.format("Can't instantiate an object %s", entityClass.getName()), ex);
        }
    }

    public Builder<T> with(Consumer<T> setter){
        setter.accept(instance);
        return this;
    }

    public T get(){
        return instance;
    }
}
