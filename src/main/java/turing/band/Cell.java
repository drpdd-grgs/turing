package turing.band;

public class Cell {

    private char symbol;
    private Cell left;
    private Cell right;

    public Cell(char c) {
        symbol = c;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char c) {
        symbol = c;
    }

    public Cell getLeft() {
        return left;
    }

    public void setLeft(Cell cell) {
        left = cell;
    }

    public Cell getRight() {
        return right;
    }

    public void setRight(Cell cell) {
        right = cell;
    }

}
