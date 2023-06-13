package turing.band;

public class EmptyState implements BandState {

    private final Band band;

    public EmptyState(Band currentBand) {
        band = currentBand;
    }

    @Override
    public void addRight(char c) {
        band.head = new Cell(c);
        band.setState(new SingleElementState(band));
    }

    @Override
    public void addLeft(char c) {
        band.head = new Cell(c);
        band.setState(new SingleElementState(band));
    }

}
