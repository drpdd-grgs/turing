package turing.alphabet;

public class Alphabet {

    public static char BLANK = '_';

    private Node root;

    public Alphabet(String symbols) {
        if (symbols.length() == 0) {
            throw new IllegalArgumentException("Cant create empty tree!");
        }
        for (int i = 0; i < symbols.length(); i++) {
            this.add(symbols.charAt(i));
        }
    }

    public void add(char c) {
        root = addRecursive(root, c);
    }

    public boolean contains(char c) {
        return containsRecursive(root, c);
    }

    private Node addRecursive(Node node, char c) {
        if (node == null) {
            return new Node(c);
        }
        if (c < node.getValue()) {
            node.setLeft(addRecursive(node.getLeft(), c));
        }
        if (c > node.getValue()) {
            node.setRight(addRecursive(node.getRight(), c));
        }
        return node;
    }

    private boolean containsRecursive(Node node, char c) {
        if (node == null) {
            return false;
        }
        if (c == node.getValue()) {
            return true;
        }
        return c < node.getValue()
                ? containsRecursive(node.getLeft(), c)
                : containsRecursive(node.getRight(), c);
    }

}
