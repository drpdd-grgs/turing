package turing.util.avltree;

public class AVLTree<T extends Comparable> {

    protected Node<T> root;

    public AVLTree() {

    }

    public AVLTree(T[] items) {
        for (T item : items) {
            this.add(item);
        }
    }

    public void add(T value) {
        root = addRecursive(root, value);
    }

    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    public T find(T value) {
        return findRecursive(root, value);
    }

    private Node addRecursive(Node<T> node, T value) {
        if (node == null) {
            return new Node(value);
        }
        if (lessThan(value, node.getValue())) {
            node.setLeft(addRecursive(node.getLeft(), value));
        }
        if (greaterThan(value, node.getValue())) {
            node.setRight(addRecursive(node.getRight(), value));
        }
        return rebalance(node);
    }

    private boolean containsRecursive(Node<T> node, T value) {
        if (node == null) {
            return false;
        }
        if (equals(value, node.getValue())) {
            return true;
        }
        return lessThan(value, node.getValue())
                ? containsRecursive(node.getLeft(), value)
                : containsRecursive(node.getRight(), value);
    }

    private T findRecursive(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (equals(value, node.getValue())) {
            return value;
        }
        return lessThan(value, node.getValue())
                ? findRecursive(node.getLeft(), value)
                : findRecursive(node.getRight(), value);
    }

    private void updateHeight(Node<T> node) {
        node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));
    }

    private int getHeight(Node<T> node) {
        return node == null
                ? -1
                : node.getHeight();
    }

    private int getBalance(Node<T> node) {
        return node == null
                ? 0
                : getHeight(node.getRight()) - getHeight(node.getLeft());
    }

    private Node<T> rotateRight(Node<T> y) {
        Node x = y.getLeft();
        Node z = x.getRight();
        x.setRight(y);
        y.setLeft(z);
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<T> rotateLeft(Node<T> y) {
        Node x = y.getRight();
        Node z = x.getLeft();
        x.setLeft(y);
        y.setRight(z);
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<T> rebalance(Node<T> z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (getHeight(z.getRight().getRight()) <= getHeight(z.getRight().getLeft())) {
                z.setRight(rotateRight(z.getRight()));
            }
            z = rotateLeft(z);
        } else if (balance < -1) {
            if (getHeight(z.getLeft().getLeft()) <= getHeight(z.getLeft().getRight())) {
                z.setLeft(rotateLeft(z.getLeft()));
            }
            z = rotateRight(z);
        }
        return z;
    }

    private boolean equals(T left, T right) {
        return left.compareTo(right) == 0;
    }

    private boolean greaterThan(T left, T right) {
        return left.compareTo(right) > 0;
    }

    private boolean lessThan(T left, T right) {
        return left.compareTo(right) < 0;
    }

}
