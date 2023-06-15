package turing.machine.head;

import turing.machine.band.Band;
import turing.util.avltree.StateTree;
import turing.util.constant.Direction;
import turing.machine.transition.Transition;

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

    public void setBand(Band band) {
        this.band = band;
    }

    public void start() {
        if (state != null) {
            while (isNotTerminalState) {
                state.doTransition(band.getCurrentSymbol());
            }
        }
    }

    public void addTransition(int stateNum,
                              char stateSymbol,
                              int stateNumToSet,
                              char symbolToSet,
                              Direction direction) {
        State state = stateSpace.findByStateNum(stateNum);
        if (state.containsTransitionByStateSymbol(stateSymbol)) {
            throw new IllegalArgumentException("Transition for Symbol '" + stateSymbol + "' and State with num '" + stateNum + "' already exists!");
        }
        state.addTransition(new Transition(stateSymbol, stateNumToSet, symbolToSet, direction));
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

    protected void setSymbolToCurrentCell(char symbol) {
        band.setSymbolToCurrentCell(symbol);
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
