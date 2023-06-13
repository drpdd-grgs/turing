package turing.head;

import turing.avltree.TransitionTree;
import turing.transition.Transition;

public class State implements Comparable<State> {

    private final int stateNum;
    private final Head head;
    private final TransitionTree transitionSpace;

    protected State(Head headForState) {
        head = headForState;
        head.increaseStateCount();
        stateNum = head.getStateCount();
        transitionSpace = new TransitionTree();
    }

    public int getStateNum() {
        return stateNum;
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(stateNum, o.getStateNum());
    }

    protected boolean containsTransitionBySymbol(char symbol) {
        return transitionSpace.findBySymbolToRead(symbol) != null;
    }

    protected void addTransition(Transition transition) {
        transitionSpace.add(transition);
    }

    protected void doTransition(char symbol) {
        Transition transition = transitionSpace.findBySymbolToRead(symbol);
        if (transition != null) {
            head.writeToBand(transition.getSymbolToWrite());
            head.moveHead(transition.getDirection());
            head.setState(transition.getStateNumToSet());
        } else {
            head.terminate();
        }
    }

}
