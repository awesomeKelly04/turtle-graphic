package turtleGraphic;

import turtleTestException.TurtleException;

import java.util.logging.Logger;

public class Turtle {

    private static final Logger LOGGER = Logger.getLogger(Turtle.class.getName());
    private Pen myTurtlePen;
    private Orientation turtleOrientation = Orientation.COLUMN_RIGHT;
    private int columnCoordinate;
    private int rowCoordinate;

    public Pen getMyTurtlePen() {
        return myTurtlePen;
    }

    public void setMyTurtlePen(Pen myTurtlePen) {
        this.myTurtlePen = myTurtlePen;
    }

    public Orientation getTurtleOrientation() {
        return turtleOrientation;
    }

    public void setTurtleOrientation(Orientation turtleOrientation) {
        this.turtleOrientation = turtleOrientation;
    }

    public int getColumnCoordinate() {
        return columnCoordinate;
    }

    public void setColumnCoordinate(int columnCoordinate) {
        this.columnCoordinate = columnCoordinate;
    }

    public int getRowCoordinate() {
        return rowCoordinate;
    }

    public void setRowCoordinate(int rowCoordinate) {
        this.rowCoordinate = rowCoordinate;
    }

    public void moveForward(int numbersOfSteps){
        this.columnCoordinate = numbersOfSteps - 1;
    }

    public void draw(int numberOfSteps, SketchPad drawingPad) throws TurtleException {

        boolean drawingPadValidated = TurtleValidator.isValidSketchPad(drawingPad);
        if(!drawingPadValidated) {
            String errorMessage = "Sketchpad for the turtle to draw on cannot be null";
            LOGGER.severe(errorMessage);
            throw new TurtleException(errorMessage);
        }

        int floorBoundary = drawingPad.getFloor().length;
        int currentCoordinate = this.columnCoordinate;
        if(this.turtleOrientation == Orientation.ROW_LEFT || this.turtleOrientation == Orientation.ROW_RIGHT) {
            currentCoordinate = this.rowCoordinate;
        }

        boolean numberOfStepsValidated = TurtleValidator.isValidNumberOfSteps(numberOfSteps, floorBoundary, currentCoordinate, this.turtleOrientation);
        if(!numberOfStepsValidated) {
            String errorMessage = "Method draw was called with invalid number of steps " + numberOfSteps;
            LOGGER.info(errorMessage);
            throw new TurtleException(errorMessage);
        }

        for (int i = 0; i < numberOfSteps; i++){
            if (this.getMyTurtlePen().getPenPosition() == Position.DOWN) {
                drawingPad.getFloor()[this.rowCoordinate][this.columnCoordinate] = 1;
            } else {
                drawingPad.getFloor()[this.rowCoordinate][this.columnCoordinate] = 0;
            }
            if (this.turtleOrientation == Orientation.COLUMN_RIGHT && i != numberOfSteps - 1){
                this.columnCoordinate++;
            }
            else if (this.turtleOrientation == Orientation.ROW_RIGHT && i != numberOfSteps - 1){
                this.rowCoordinate++;
            }
            else if (this.turtleOrientation == Orientation.COLUMN_LEFT && i != numberOfSteps - 1){
                this.columnCoordinate--;
            }
            else if (this.turtleOrientation == Orientation.ROW_LEFT && i != numberOfSteps - 1){
                this.rowCoordinate--;
            }
        }
    }
}
