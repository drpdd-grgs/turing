package turing.machine.band;

public class EmptyState implements State {

    private final Band band;

    public EmptyState(Band currentBand) {
        band = currentBand;
    }

    @Override
    public void addRight(char symbol) {
        band.head = new Cell(symbol);
        band.setState(new SingleElementState(band));
        band.current = band.head;
    }

    @Override
    public void addLeft(char symbol) {
        band.head = new Cell(symbol);
        band.setState(new SingleElementState(band));
        band.current = band.head;
    }

}
