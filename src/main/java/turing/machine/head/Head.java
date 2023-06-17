package turing.machine.head;

import turing.machine.band.Band;
import turing.util.avltree.Alphabet;
import turing.util.avltree.StateTree;
import turing.util.constant.Direction;

public class Head {

    private static int stateCount = -1;

    private final StateTree stateTree;
    private State state;
    private boolean isTerminalState;
    private Band band;
    private final Alphabet alphabet;

    public Head(Alphabet alphabet, int countOfStates) {
        this(alphabet, countOfStates, 1);
    }

    public Head(Alphabet alphabet, int countOfStates, int startStateNum) {
        this.alphabet = alphabet;
        stateTree = new StateTree();
        initStates(countOfStates);
        state = stateTree.findByStateNum(startStateNum);
        isTerminalState = false;
    }

    public void start() {
        validateStartCondition();
        while (!isTerminalState) {
            state.doTransition(band.getCurrentSymbol());
        }
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public void addTransition(int stateNum,
                              char stateSymbol,
                              int stateNumToSet,
                              char symbolToSet,
                              Direction direction) {
        validateStateNum(stateNum);
        validateStateNum(stateNumToSet);
        alphabet.validateSymbolWithThrow(stateSymbol);
        alphabet.validateSymbolWithThrow(symbolToSet);
        State state = stateTree.findByStateNum(stateNum);
        state.addTransition(stateSymbol, stateNumToSet, symbolToSet, direction);
    }

    public int getStateCount() {
        return stateCount;
    }

    public boolean isTerminalState() {
        return isTerminalState;
    }

    protected void increaseStateCount() {
        stateCount++;
    }

    protected void setState(int stateNum) {
        if (stateNum != state.getStateNum()) {
            state = stateTree.findByStateNum(stateNum);
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
        isTerminalState = true;
    }

    private void initStates(int countOfStates) {
        stateCount = 0;
        for (int i = 0; i < countOfStates; i++) {
            stateTree.add(new State(this));
        }
    }

    private void validateStateNum(int stateNum) {
        if (stateNum < 1 && stateNum > stateCount) {
            throw new IllegalArgumentException("Incorrect stateNum!");
        }
    }

    private void validateStartCondition() {
        if (state == null) {
            throw new IllegalStateException("State for Head is not defined");
        }
        if (band == null) {
            throw new IllegalStateException("Band is null");
        }
    }

}
