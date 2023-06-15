package turing.util.avltree;

import turing.machine.head.State;

public class StateTree extends AVLTree<State> {

    public StateTree() {
        super();
    }

    public State findByStateNum(int stateNum) {
        return findByStateNumRecursive(root, stateNum);
    }

    private State findByStateNumRecursive(Node<State> node, int stateNum) {
        if (node == null) {
            return null;
        }
        if (stateNum == node.getValue().getStateNum()) {
            return node.getValue();
        }
        return stateNum < node.getValue().getStateNum()
                ? findByStateNumRecursive(node.getLeft(), stateNum)
                : findByStateNumRecursive(node.getRight(), stateNum);
    }

}
