package turing.avltree;

public class Node<T extends Comparable> {

    private final T value;
    private Node left;
    private Node right;
    private int height;

    protected Node(T c) {
        value = c;
    }

    public T getValue() {
        return value;
    }

    protected Node<T> getLeft() {
        return left;
    }

    protected Node<T> getRight() {
        return right;
    }

    protected int getHeight() {
        return height;
    }

    protected void setLeft(Node node) {
        left = node;
    }

    protected void setRight(Node node) {
        right = node;
    }

    protected void setHeight(int value) {
        height = value;
    }

}
