package turing.machine.head;

import turing.util.avltree.TransitionTree;
import turing.machine.transition.Transition;

public class State implements Comparable<State> {

    private final int stateNum;
    private final Head head;
    private final TransitionTree transitionSpace;

    protected State(Head head) {
        this.head = head;
        this.head.increaseStateCount();
        stateNum = this.head.getStateCount();
        transitionSpace = new TransitionTree();
    }

    public int getStateNum() {
        return stateNum;
    }

    @Override
    public int compareTo(State state) {
        return Integer.compare(stateNum, state.getStateNum());
    }

    protected boolean containsTransitionByStateSymbol(char symbol) {
        return transitionSpace.findByStateSymbol(symbol) != null;
    }

    protected void addTransition(Transition transition) {
        transitionSpace.add(transition);
    }

    protected void doTransition(char symbol) {
        Transition transition = transitionSpace.findByStateSymbol(symbol);
        if (transition != null) {
            head.setSymbolToCurrentCell(transition.getSymbolToSet());
            head.moveHead(transition.getDirection());
            head.setState(transition.getStateNumToSet());
        } else {
            head.terminate();
        }
    }

}
