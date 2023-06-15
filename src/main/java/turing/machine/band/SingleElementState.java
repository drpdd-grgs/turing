package turing.machine.band;

public class SingleElementState implements State {

    private final Band band;

    public SingleElementState(Band currentBand) {
        band = currentBand;
    }

    @Override
    public void addRight(char symbol) {
        band.tail = new Cell(symbol);
        band.head.setRight(band.tail);
        band.tail.setLeft(band.head);
        band.setState(new MultiElementState(band));
    }

    @Override
    public void addLeft(char symbol) {
        band.tail = band.head;
        band.head = new Cell(symbol);
        band.head.setRight(band.tail);
        band.tail.setLeft(band.head);
        band.setState(new MultiElementState(band));
    }

}
