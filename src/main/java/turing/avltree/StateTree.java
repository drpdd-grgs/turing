package turing.avltree;

import turing.head.State;

public class StateTree extends AVLTree<State> {

    public StateTree() {
        super();
    }

    public State findByStateNum(int stateNum) {
        return findByStateNumRecursive(root, stateNum);
    }

    private State findByStateNumRecursive(Node<State> rootNode, int stateNum) {
        if (rootNode == null) {
            return null;
        }
        if (stateNum == rootNode.getValue().getStateNum()) {
            return rootNode.getValue();
        }
        return stateNum < rootNode.getValue().getStateNum()
                ? findByStateNumRecursive(rootNode.getLeft(), stateNum)
                : findByStateNumRecursive(rootNode.getRight(), stateNum);
    }

}
