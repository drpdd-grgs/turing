package turing.head;

import turing.band.Band;
import turing.avltree.StateTree;
import turing.constants.Direction;
import turing.transition.Transition;

public class Head {

    private static int stateCount = -1;

    private final Band band;
    private final StateTree stateSpace;
    private State state;
    private boolean isNotTerminalState;

    public Head(Band bandToProcess, int countOfStates, int startStateNum) {
        band = bandToProcess;
        stateSpace = new StateTree();
        stateCount = 0;
        initStates(countOfStates);
        isNotTerminalState = true;
        state = stateSpace.findByStateNum(startStateNum);
    }

    public void start() {
        if (state != null) {
            while (isNotTerminalState) {
                state.doTransition(band.getCurrentSymbol());
            }
        }
    }

    public void addTransitionRule(int stateNum,
                                  char symbolToRead,
                                  int stateNumToSet,
                                  char symbolToWrite,
                                  Direction direction) {
        State state = stateSpace.findByStateNum(stateNum);
        state.addTransition(new Transition(symbolToRead, stateNumToSet, symbolToWrite, direction));
    }

    protected int getStateCount() {
        return stateCount;
    }

    protected void increaseStateCount() {
        stateCount++;
    }

    protected void setState(int stateNum) {
        if (stateNum != state.getStateNum()) {
            state = stateSpace.findByStateNum(stateNum);
        }
    }

    protected void writeToBand(char symbol) {
        band.writeSymbol(symbol);
    }

    protected void moveHead(Direction direction) {
        if (direction == Direction.LEFT) {
            band.moveLeft();
        }
        if (direction == Direction.RIGHT) {
            band.moveRight();
        }
    }

    protected void terminate() {
        isNotTerminalState = false;
    }

    private void initStates(int countOfStates) {
        for (int i = 0; i < countOfStates; i++) {
            stateSpace.add(new State(this));
        }
    }

}
