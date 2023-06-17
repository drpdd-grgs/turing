package turing.machine.head;

import turing.util.avltree.TransitionTree;
import turing.machine.transition.Transition;
import turing.util.constant.Direction;

public class State implements Comparable<State> {

    private final int stateNum;
    private final Head head;
    private final TransitionTree transitionTree;

    protected State(Head head) {
        this.head = head;
        this.head.increaseStateCount();
        stateNum = this.head.getStateCount();
        transitionTree = new TransitionTree();
    }

    public int getStateNum() {
        return stateNum;
    }

    @Override
    public int compareTo(State state) {
        return Integer.compare(stateNum, state.getStateNum());
    }

    protected void addTransition(char stateSymbol,
                                 int stateNumToSet,
                                 char symbolToSet,
                                 Direction direction) {
        validateStateSymbol(stateSymbol);
        transitionTree.add(new Transition(stateSymbol, stateNumToSet, symbolToSet, direction));
    }

    protected void doTransition(char symbol) {
        Transition transition = transitionTree.findByStateSymbol(symbol);
        if (transition != null) {
            head.setSymbolToCurrentCell(transition.getSymbolToSet());
            head.moveHead(transition.getDirection());
            head.setState(transition.getStateNumToSet());
        } else {
            head.terminate();
        }
    }

    private void validateStateSymbol(char stateSymbol) {
        if (containsTransitionByStateSymbol(stateSymbol)) {
            throw new IllegalArgumentException("Transition for Symbol '" + stateSymbol + "' and State with num '" + stateNum + "' already exists!");
        }
    }

    private boolean containsTransitionByStateSymbol(char symbol) {
        return transitionTree.findByStateSymbol(symbol) != null;
    }

}
