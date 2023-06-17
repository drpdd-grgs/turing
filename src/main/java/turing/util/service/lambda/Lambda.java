package turing.util.service.lambda;

// TODO: Remove. Might be used for dependency injection usage of machine.
public interface Lambda<T> {

    boolean conditionCheck(String value);
    T getValue();

}
