package logic;

import models.Transport;

import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DispatchRule<T extends Transport> implements Serializable {
    private final String ruleName;
    private final Class<T> targetClass;
    private final Predicate<T> condition;
    private final Consumer<T> action;

    public DispatchRule(String ruleName, Class<T> targetClass, Predicate<T> condition, Consumer<T> action) {
        this.ruleName = ruleName;
        this.targetClass = targetClass;
        this.condition = condition;
        this.action = action;
    }

    public DispatchRule(String ruleName, Predicate<T> condition, Consumer<T> action) {
        this.ruleName = ruleName;
        this.targetClass = null;
        this.condition = condition;
        this.action = action;
    }

    public void apply(T t){
        if (condition.test(t)){
            action.accept(t);
        }
    }

    public String getRuleName(){
        return ruleName;
    }

    public Class<T> getTargetClass(){
        return targetClass;
    }
}
