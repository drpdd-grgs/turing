package turing.band;

import turing.avltree.AVLTree;
import turing.constants.Constants;

import java.util.NoSuchElementException;

public class Band implements Iterable<Cell> {

    private BandState state;

    protected Cell head;
    protected Cell tail;
    private Cell current;

    public Band(AVLTree<Character> alphabet, String bandSymbols) {
        state = new EmptyState(this);
        for (int i = 0; i < bandSymbols.length(); i++) {
            if (alphabet.contains(bandSymbols.charAt(i))) {
                addRight(bandSymbols.charAt(i));
            } else {
                throw new IllegalArgumentException("Alphabet is not contains symbol: " + bandSymbols.charAt(i));
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

    protected void setState(BandState newState) {
        state = newState;
    }

    public void writeSymbol(char symbol) {
        current.setSymbol(symbol);
    }

    private void addRight(char c) {
        state.addRight(c);
    }

    private void addLeft(char c) {
        state.addLeft(c);
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
                throw new NoSuchElementException("There is no next element in Band");
            }
            Cell item = current;
            current = current.getRight();
            return item;
        }
    }

}
