package turing.util.service.lambda;

public interface Lambda<T> {

    boolean conditionCheck(String value);
    T getValue();

}
