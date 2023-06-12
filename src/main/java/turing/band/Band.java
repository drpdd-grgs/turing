package turing.band;

import turing.alphabet.Alphabet;

public class Band {

    private BandState state;

    protected Cell head;
    protected Cell tail;
    private Cell current;

    private final Alphabet alphabet;

    public Band(String alphabetSymbols, String bandSymbols) {
        if (bandSymbols.length() > 0 || alphabetSymbols.length() > 0) {
            state = new EmptyState(this);
            alphabet = new Alphabet(alphabetSymbols);
            for (int i = 0; i < bandSymbols.length(); i++) {
                if (alphabet.contains(bandSymbols.charAt(i))) {
                    addRight(bandSymbols.charAt(i));
                } else {
                    throw new IllegalArgumentException("Alphabet is not contains symbol: " + bandSymbols.charAt(i));
                }
            }
            alphabet.add(Alphabet.BLANK);
            current = head;
        } else {
            throw new IllegalArgumentException("Band and alphabet must be not empty!");
        }
    }

    public Cell getCurrent() {
        return current;
    }

    public void moveRight() {
        if (current.getRight() == null) {
            addRight(Alphabet.BLANK);
        }
        current = current.getRight();
    }

    public void moveLeft() {
        if (current.getLeft() == null) {
            addLeft(Alphabet.BLANK);
        }
        current = current.getLeft();
    }

    protected void changeState(BandState newState) {
        state = newState;
    }

    private void addRight(char c) {
        state.addRight(c);
    }

    private void addLeft(char c) {
        state.addLeft(c);
    }

}
