package turing.util.avltree;

public class Alphabet extends AVLTree<Character> {

    public Alphabet() {
        super();
    }

    public Alphabet(String symbols) {
        this(symbols.toCharArray());
    }

    public Alphabet(char[] symbols) {
        super();
        for (char symbol : symbols) {
            add(symbol);
        }
    }

    public Alphabet(Character[] symbols) {
        super(symbols);
    }

    public void validateSymbolWithThrow(char symbol) {
        if (!contains(symbol)) {
            throw new IllegalArgumentException("Alphabet is not contains symbol: " + symbol);
        }
    }

}
