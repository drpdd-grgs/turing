package turing.util.avltree;

import turing.machine.transition.Transition;

public class TransitionTree extends AVLTree<Transition> {

    public TransitionTree() {
        super();
    }

    public Transition findByStateSymbol(char symbol) {
        return findByStateSymbolRecursive(root, symbol);
    }

    private Transition findByStateSymbolRecursive(Node<Transition> node, char symbol) {
        if (node == null) {
            return null;
        }
        if (symbol == node.getValue().getStateSymbol()) {
            return node.getValue();
        }
        return symbol < node.getValue().getStateSymbol()
                ? findByStateSymbolRecursive(node.getLeft(), symbol)
                : findByStateSymbolRecursive(node.getRight(), symbol);
    }

}
