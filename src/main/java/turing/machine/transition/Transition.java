package turing.machine.transition;

import turing.util.constant.Direction;

public class Transition implements Comparable<Transition> {

    private final char stateSymbol;
    private final int stateNumToSet;
    private final char symbolToSet;
    private final Direction direction;

    public Transition(char stateSymbol,
                      int stateNumToSet,
                      char symbolToSet,
                      Direction directionForTransition) {
        this.stateSymbol = stateSymbol;
        this.symbolToSet = symbolToSet;
        direction = directionForTransition;
        this.stateNumToSet = stateNumToSet;
    }

    public char getStateSymbol() {
        return stateSymbol;
    }

    public int getStateNumToSet() {
        return stateNumToSet;
    }

    public char getSymbolToSet() {
        return symbolToSet;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public int compareTo(Transition o) {
        return Character.compare(stateSymbol, o.getStateSymbol());
    }

}
