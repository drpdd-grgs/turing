package turing.band;

public class SingleElementState implements BandState {

    private final Band band;

    public SingleElementState(Band currentBand) {
        band = currentBand;
    }

    @Override
    public void addRight(char c) {
        band.tail = new Cell(c);
        band.head.setRight(band.tail);
        band.tail.setLeft(band.head);
        band.setState(new MultiElementState(band));
    }

    @Override
    public void addLeft(char c) {
        band.tail = band.head;
        band.head = new Cell(c);
        band.head.setRight(band.tail);
        band.tail.setLeft(band.head);
        band.setState(new MultiElementState(band));
    }

}
