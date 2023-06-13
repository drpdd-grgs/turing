package turing.constant;

public enum Direction {
    LEFT,
    RIGHT,
    NONE;

    public static Direction getDirectionByChar(char c) {
        switch (c) {
            case Constants.RIGHT:
                return RIGHT;
            case Constants.LEFT:
                return LEFT;
            case Constants.NONE:
                return NONE;
        }
        return null;
    }

}
