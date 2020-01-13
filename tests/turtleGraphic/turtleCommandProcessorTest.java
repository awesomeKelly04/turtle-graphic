package turtleGraphic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import turtleTestException.TurtleException;

import static org.junit.jupiter.api.Assertions.*;

public class turtleCommandProcessorTest {

    private TurtleCommandProcessor turtleCommandProcessor;

    @BeforeEach
    void setUp() {
        turtleCommandProcessor = new TurtleCommandProcessor(new Turtle(), new SketchPad());
        turtleCommandProcessor.getCommand();
    }

    @Test
    void turtleCommandProcessorExistsTest() {
        assertNotNull(turtleCommandProcessor);
    }

    @Test
    void turtleCommandProcessorHasCommandTest() {
        assertNotNull(turtleCommandProcessor.getCommand());
        turtleCommandProcessor.setCommand(Command.PEN_DOWN);
        assertNotNull(turtleCommandProcessor.getCommand());
    }

    @Test
    void turtleCommandProcessorSetCommandTest() {
        turtleCommandProcessor.setCommand(Command.PEN_DOWN);
        assertEquals(Command.PEN_DOWN, turtleCommandProcessor.getCommand());
    }

    @Test
    void turtleCommandPerformOperationForPenUpAndDownTest() {
        turtleCommandProcessor.setCommand(Command.PEN_UP);
        turtleCommandProcessor.performCommandOperationForPenUpAndDown(turtleCommandProcessor.getCommand());
        assertEquals(Position.UP, turtleCommandProcessor.getTurtle().getMyTurtlePen().getPenPosition());
        turtleCommandProcessor.setCommand(Command.PEN_DOWN);
        turtleCommandProcessor.performCommandOperationForPenUpAndDown(turtleCommandProcessor.getCommand());
        assertEquals(Position.DOWN, turtleCommandProcessor.getTurtle().getMyTurtlePen().getPenPosition());
    }

    @Test
    void turtleCommandPerformOperationForTurnsTest() {
        turtleCommandProcessor.setCommand(Command.TURN_RIGHT);
        turtleCommandProcessor.performCommandOperationForTurns(turtleCommandProcessor.getCommand());
        assertEquals(Orientation.COLUMN_RIGHT, turtleCommandProcessor.getTurtle().getTurtleOrientation());
        turtleCommandProcessor.setCommand(Command.TURN_LEFT);
        turtleCommandProcessor.performCommandOperationForTurns(turtleCommandProcessor.getCommand());
        assertEquals(Orientation.COLUMN_RIGHT, turtleCommandProcessor.getTurtle().getTurtleOrientation());
    }

    @Test
    void turtleCommandPerformOperationForMovesTest() {
        turtleCommandProcessor.getTurtle().getMyTurtlePen().setPenPosition(Position.DOWN);
        turtleCommandProcessor.setCommand(Command.MOVE_FORWARD);
        turtleCommandProcessor.setNumberOfSteps(12);
        turtleCommandProcessor.getTurtle().setRowCoordinate(14);
        turtleCommandProcessor.getTurtle().setColumnCoordinate(8);
        try {
            turtleCommandProcessor.performCommandOperationForMoves(Command.TURN_LEFT);
        }catch (TurtleException turtleEx){
            turtleEx.printStackTrace();
        }

        assertEquals(14, turtleCommandProcessor.getTurtle().getRowCoordinate());
        assertEquals(19, turtleCommandProcessor.getTurtle().getColumnCoordinate());
        for (int i = 0; i < 12; i++){
            assertEquals(1, turtleCommandProcessor.getSketchPad().getFloor()[14][i+8]);
        }

        try {
            turtleCommandProcessor.performCommandOperationForMoves(Command.TURN_LEFT);
        }catch (TurtleException turtleEx){
            turtleEx.printStackTrace();
        }
        assertEquals(3, turtleCommandProcessor.getTurtle().getRowCoordinate());
        assertEquals(19, turtleCommandProcessor.getTurtle().getColumnCoordinate());
        for (int i = 0; i < 12; i++){
            assertEquals(1, turtleCommandProcessor.getSketchPad().getFloor()[i+3][19]);
        }

        try {
            turtleCommandProcessor.performCommandOperationForMoves(Command.TURN_LEFT);
        }catch (TurtleException turtleEx){
            turtleEx.printStackTrace();
        }
        assertEquals(3, turtleCommandProcessor.getTurtle().getRowCoordinate());
        assertEquals(8, turtleCommandProcessor.getTurtle().getColumnCoordinate());
        for (int i = 0; i < 12; i++){
            assertEquals(1, turtleCommandProcessor.getSketchPad().getFloor()[3][i+8]);
        }

        try {
            turtleCommandProcessor.performCommandOperationForMoves(Command.TURN_LEFT);
        }catch (TurtleException turtleEx){
            turtleEx.printStackTrace();
        }
        assertEquals(14, turtleCommandProcessor.getTurtle().getRowCoordinate());
        assertEquals(8, turtleCommandProcessor.getTurtle().getColumnCoordinate());
        for (int i = 0; i < 12; i++){
            assertEquals(1, turtleCommandProcessor.getSketchPad().getFloor()[i+3][8]);
        }

        for (int i = 0; i < turtleCommandProcessor.getSketchPad().getFloor().length; i++){
            for (int j = 0; j < turtleCommandProcessor.getSketchPad().getFloor().length; j++){
                if (turtleCommandProcessor.getSketchPad().getFloor()[i][j] == 0){
                    System.out.print("   ");
                }
                else {
                    System.out.print("*  ");
                }
            }

            System.out.println();
        }
    }

    @Test
    void getAllTurtleCommandTest() {
        turtleCommandProcessor.displayAllTurtleCommand();
    }
}
