package turing.alphabet;

public class Node {

    private final char value;
    private Node left;
    private Node right;

    public Node(char c) {
        value = c;
    }

    public char getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node node) {
        left = node;
    }

    public void setRight(Node node) {
        right = node;
    }

}
