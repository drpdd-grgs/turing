package turing.band;

public class MultiElementState implements BandState {

    private final Band band;

    public MultiElementState(Band currentBand) {
        band = currentBand;
    }

    @Override
    public void addRight(char c) {
        band.tail.setRight(new Cell(c));
        band.tail.getRight().setLeft(band.tail);
        band.tail = band.tail.getRight();
    }

    @Override
    public void addLeft(char c) {
        band.head.setLeft(new Cell(c));
        band.head.getLeft().setRight(band.head);
        band.head = band.head.getLeft();
    }

}
