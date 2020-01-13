package turtleGraphic;

import turtleTestException.TurtleException;

public class TurtleCommandProcessor {
    private Command command;
    private Turtle turtle;
    private SketchPad sketchPad;
    private Orientation previousOrientation;
    private Command turnCommand;
    private int numberOfSteps;

    public TurtleCommandProcessor() {
    }

    public TurtleCommandProcessor(Turtle turtle, SketchPad sketchPad) {
        this.turtle = turtle;
        this.sketchPad = sketchPad;
        this.turtle.setMyTurtlePen(new Pen());
        this.command = Command.PEN_UP;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public Orientation getPreviousOrientation() {
        return previousOrientation;
    }

    public void setPreviousOrientation(Orientation previousOrientation) {
        this.previousOrientation = previousOrientation;
    }

    public Command getTurnCommand() {
        return turnCommand;
    }

    public void setTurnCommand(Command turnCommand) {
        this.turnCommand = turnCommand;
    }

    public Turtle getTurtle() {
        return turtle;
    }

    public void setTurtle(Turtle turtle) {
        this.turtle = turtle;
    }

    public SketchPad getSketchPad() {
        return sketchPad;
    }

    public void setSketchPad(SketchPad sketchPad) {
        this.sketchPad = sketchPad;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
    
    public void processUserCommand(int commandValue) throws TurtleException {
        switch (commandValue){
            case 1:
                this.command = Command.PEN_UP;
                performCommandOperationForPenUpAndDown(this.command);
                break;
            case 2:
                this.command = Command.PEN_DOWN;
                performCommandOperationForPenUpAndDown(this.command);
                break;
            case 3:
                this.command = Command.TURN_RIGHT;
                this.turnCommand = Command.TURN_RIGHT;
                performCommandOperationForTurns(this.command);
                break;
            case 4:
                this.command = Command.TURN_LEFT;
                this.turnCommand = Command.TURN_LEFT;
                performCommandOperationForTurns(this.command);
                break;
            case 5:
                this.command = Command.MOVE_FORWARD;
                if (this.turnCommand == null) {
                    performCommandOperationForMoves();
                } else {
                    performCommandOperationForMoves(this.turnCommand);
                }
                break;
            case 6:
                command = Command.DISPLAY;
                displayAllTurtleCommand();
                break;
            case 9:
                command = Command.SENTINEL;
                endOfData();
                break;
        }
    }

    public void performCommandOperationForMoves(Command command) throws TurtleException {
        performCommandOperationForTurns(command);
        turtle.draw(this.numberOfSteps, sketchPad);
        this.previousOrientation = turtle.getTurtleOrientation();
    }

    public void performCommandOperationForMoves() throws TurtleException {
        turtle.draw(this.numberOfSteps, sketchPad);
        this.previousOrientation = turtle.getTurtleOrientation();
    }

    public void performCommandOperationForPenUpAndDown(Command command) {
        turtle.getMyTurtlePen().setPenPosition(Position.UP);
        if (command == Command.PEN_DOWN){
            turtle.getMyTurtlePen().setPenPosition(Position.DOWN);
        }
    }

    public void performCommandOperationForTurns(Command command){
        switch (command){
            case TURN_RIGHT:
                if (this.previousOrientation == Orientation.ROW_RIGHT){
                    turtle.setTurtleOrientation(Orientation.COLUMN_LEFT);
                }
                else if (this.previousOrientation  == null ){
                    turtle.setTurtleOrientation(Orientation.COLUMN_RIGHT);
                }
                else if (this.previousOrientation == Orientation.COLUMN_RIGHT || turtle.getTurtleOrientation() == Orientation.COLUMN_RIGHT){
                    turtle.setTurtleOrientation(Orientation.ROW_RIGHT);
                }
                else if (this.previousOrientation == Orientation.COLUMN_LEFT){
                    turtle.setTurtleOrientation(Orientation.ROW_LEFT);
                }
                break;
            case TURN_LEFT:
                if (this.previousOrientation == Orientation.COLUMN_RIGHT){
                    turtle.setTurtleOrientation(Orientation.ROW_LEFT);
                }
                else if (this.previousOrientation  == null ){
                    turtle.setTurtleOrientation(Orientation.COLUMN_RIGHT);
                }
                else if (this.previousOrientation == Orientation.ROW_LEFT){
                    turtle.setTurtleOrientation(Orientation.COLUMN_LEFT);
                }
                else if (this.previousOrientation == Orientation.COLUMN_LEFT || turtle.getTurtleOrientation() == Orientation.COLUMN_LEFT){
                    turtle.setTurtleOrientation(Orientation.ROW_RIGHT);
                }
                break;

        }
    }

    public void displayAllTurtleCommand() {
        for (int i = 0; i < this.sketchPad.getFloor().length; i++){
            for (int j = 0; j < this.sketchPad.getFloor().length; j++){
                if (this.sketchPad.getFloor()[i][j] == 0){
                    System.out.print("   ");
                }
                else {
                    System.out.print("*  ");
                }
            }

            System.out.println();
        }
    }

    public void endOfData(){
        System.exit(9);
    }
}
