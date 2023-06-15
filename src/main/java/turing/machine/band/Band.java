package turing.machine.band;

import turing.util.avltree.AVLTree;
import turing.util.constant.Constants;

public class Band implements Iterable<Cell> {

    private State state;

    protected Cell head;
    protected Cell tail;
    private Cell current;

    public Band(AVLTree<Character> alphabet, String symbols) {
        state = new EmptyState(this);
        for (int i = 0; i < symbols.length(); i++) {
            if (alphabet.contains(symbols.charAt(i))) {
                addRight(symbols.charAt(i));
            } else {
                throw new IllegalArgumentException("Alphabet is not contains symbol: " + symbols.charAt(i));
            }
        }
        if (head == null) {
            addRight(Constants.BLANK);
        }
        current = head;
    }

    public char getCurrentSymbol() {
        return current.getSymbol();
    }

    public void moveRight() {
        if (current.getRight() == null) {
            addRight(Constants.BLANK);
        }
        current = current.getRight();
    }

    public void moveLeft() {
        if (current.getLeft() == null) {
            addLeft(Constants.BLANK);
        }
        current = current.getLeft();
    }

    public void setSymbolToCurrentCell(char symbol) {
        current.setSymbol(symbol);
    }

    public void add(char symbol) {
        addRight(symbol);
    }

    protected void setState(State state) {
        this.state = state;
    }

    private void addRight(char symbol) {
        state.addRight(symbol);
    }

    private void addLeft(char symbol) {
        state.addLeft(symbol);
    }

    @Override
    public java.util.Iterator<Cell> iterator() {
        return new Iterator(this);
    }

    public static class Iterator implements java.util.Iterator<Cell> {

        private Cell current;

        protected Iterator(Band band) {
            current = band.head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Cell next() {
            if (!hasNext()) {
                throw new IllegalStateException("There is no next element in the band");
            }
            Cell item = current;
            current = current.getRight();
            return item;
        }
    }

}
