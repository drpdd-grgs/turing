package turing.transition;

import turing.constants.Direction;

public class Transition implements Comparable<Transition> {

    private final char symbolToRead;
    private final int stateNumToSet;
    private final char symbolToWrite;
    private final Direction direction;

    public Transition(char symbolToReadForTransition,
                      int stateNumToSetForTransition,
                      char symbolToWriteForTransition,
                      Direction directionForTransition) {
        symbolToRead = symbolToReadForTransition;
        symbolToWrite = symbolToWriteForTransition;
        direction = directionForTransition;
        stateNumToSet = stateNumToSetForTransition;
    }

    public char getSymbolToRead() {
        return symbolToRead;
    }

    public int getStateNumToSet() {
        return stateNumToSet;
    }

    public char getSymbolToWrite() {
        return symbolToWrite;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public int compareTo(Transition o) {
        return Character.compare(symbolToRead, o.getSymbolToRead());
    }

}
