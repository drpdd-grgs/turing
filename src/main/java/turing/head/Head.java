package turing.head;

import turing.band.Band;

public class Head {

    private HeadState state;
    private Band band;

    private static int stateCount = -1;

    public Head(HeadState startHeadState, Band bandToProcess) {
        state = startHeadState;
        band = bandToProcess;
        stateCount = 0;
    }

    public void increaseStateCount() {
        stateCount++;
    }

}
