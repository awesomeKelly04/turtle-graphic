package turtleGraphic;

public enum Command {

    PEN_UP(1),
    PEN_DOWN(2),
    TURN_RIGHT(3),
    TURN_LEFT(4),
    MOVE_FORWARD(5),
    DISPLAY(6),
    SENTINEL(9);

    private final int value;

    Command(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
