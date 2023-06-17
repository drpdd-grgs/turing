package turing.machine.band;

import turing.util.avltree.Alphabet;
import turing.util.constant.Constants;

public class Band implements Iterable<Cell> {

    private State state;
    private final Alphabet alphabet;
    protected Cell head;
    protected Cell tail;
    protected Cell current;

    public Band(Alphabet alphabet) {
        state = new EmptyState(this);
        this.alphabet = alphabet;
    }

    public Band(Alphabet alphabet, String symbols) {
        this(alphabet, symbols.toCharArray());
    }

    public Band(Alphabet alphabet, char[] symbols) {
        this(alphabet);
        for (char symbol : symbols) {
            addRight(symbol);
        }
    }

    public Band(Alphabet alphabet, Character[] symbols) {
        this(alphabet);
        for (char symbol : symbols) {
            addRight(symbol);
        }
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

    public void addRight(char symbol) {
        alphabet.validateSymbolWithThrow(symbol);
        state.addRight(symbol);
    }

    public void addLeft(char symbol) {
        alphabet.validateSymbolWithThrow(symbol);
        state.addLeft(symbol);
    }

    public char getCurrentSymbol() {
        return current.getSymbol();
    }

    public void setSymbolToCurrentCell(char symbol) {
        current.setSymbol(symbol);
    }

    protected void setState(State state) {
        this.state = state;
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
