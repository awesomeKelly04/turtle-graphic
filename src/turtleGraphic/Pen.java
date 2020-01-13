package turtleGraphic;

public class Pen {
    private Position penPosition = Position.UP;

    public Position getPenPosition() {
        return penPosition;
    }

    public void setPenPosition(Position penPosition) {
        this.penPosition = penPosition;
    }
}
