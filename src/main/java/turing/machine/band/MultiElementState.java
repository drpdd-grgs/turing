package turing.machine.band;

public class MultiElementState implements State {

    private final Band band;

    public MultiElementState(Band currentBand) {
        band = currentBand;
    }

    @Override
    public void addRight(char symbol) {
        band.tail.setRight(new Cell(symbol));
        band.tail.getRight().setLeft(band.tail);
        band.tail = band.tail.getRight();
    }

    @Override
    public void addLeft(char symbol) {
        band.head.setLeft(new Cell(symbol));
        band.head.getLeft().setRight(band.head);
        band.head = band.head.getLeft();
    }

}
