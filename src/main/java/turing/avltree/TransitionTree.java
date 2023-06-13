package turing.avltree;

import turing.transition.Transition;

public class TransitionTree extends AVLTree<Transition> {

    public TransitionTree() {

    }

    public Transition findBySymbolToRead(char symbol) {
        return findBySymbolToReadRecursive(root, symbol);
    }

    private Transition findBySymbolToReadRecursive(Node<Transition> rootNode, char symbol) {
        if (rootNode == null) {
            return null;
        }
        if (symbol == rootNode.getValue().getSymbolToRead()) {
            return rootNode.getValue();
        }
        return symbol < rootNode.getValue().getSymbolToRead()
                ? findBySymbolToReadRecursive(rootNode.getLeft(), symbol)
                : findBySymbolToReadRecursive(rootNode.getRight(), symbol);
    }

}
