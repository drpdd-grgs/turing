package turing.head;

import turing.band.Band;
import turing.avltree.StateTree;
import turing.constant.Direction;
import turing.transition.Transition;

public class Head {

    private static int stateCount = -1;

    private Band band;
    private final StateTree stateSpace;
    private State state;
    private boolean isNotTerminalState;

    public Head(int countOfStates, int startStateNum) {
        stateSpace = new StateTree();
        stateCount = 0;
        initStates(countOfStates);
        isNotTerminalState = true;
        state = stateSpace.findByStateNum(startStateNum);
    }

    public void setBand(Band bandToProcess) {
        band = bandToProcess;
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
        if (state.containsTransitionBySymbol(symbolToRead)) {
            throw new IllegalArgumentException("Transition for Symbol '" + symbolToRead + "' and State with num '" + stateNum + "' already exists!");
        }
        state.addTransition(new Transition(symbolToRead, stateNumToSet, symbolToWrite, direction));
    }

    public int getStateCount() {
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
