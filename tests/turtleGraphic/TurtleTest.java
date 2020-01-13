package turtleGraphic;

import org.junit.jupiter.api.*;
import turtleTestException.TurtleException;

import static org.junit.jupiter.api.Assertions.*;

public class TurtleTest {

    private Turtle myTurtle;
    private Pen biro;
    private SketchPad aPad;

    @BeforeEach
    void setUp() throws Exception {
       myTurtle = new Turtle();
       biro = new Pen();
       aPad = new SketchPad();
       myTurtle.setMyTurtlePen(biro);
    }

    @AfterEach
    void tearDown() throws Exception{

    }

    @BeforeAll
    static void beforeAll() throws Exception {

    }

    @AfterAll
    static void afterAll() throws Exception {

    }

    @Test
    public void turtleExistsTest() {
        assertNotNull(myTurtle);
    }

    @Test
    void penNotNullTest(){
        assertNotNull(biro);
    }

    @Test
    void turtlePenNotNull(){
        //myTurtle.setMyTurtlePen(biro);
        assertNotNull(myTurtle.getMyTurtlePen());
    }

    @Test
    void turtlePenIsUp(){
        //myTurtle.setMyTurtlePen(biro);
        assertEquals(Position.UP, myTurtle.getMyTurtlePen().getPenPosition());
    }

    @Test
    void turtlePenPositionNotNull() {
        //myTurtle.setMyTurtlePen(biro);
        assertNotNull(myTurtle.getMyTurtlePen().getPenPosition());
    }

    @Test
    void turtleHasDirection() {
        assertNotNull(myTurtle.getTurtleOrientation());
    }

    @Test
    void turtleDirectionIsRight() {
        assertEquals(Orientation.COLUMN_RIGHT, myTurtle.getTurtleOrientation());
    }

    @Test
    void sketchPadExists() {
        assertNotNull(aPad);
    }

    @Test
    void sketchPadHasAFloor() {
        assertNotNull(aPad.getFloor());
    }

    @Test
    void turtleDefaultCoordinatesAreZero() {
        assertEquals(0, myTurtle.getColumnCoordinate());
        assertEquals(0, myTurtle.getRowCoordinate());
    }

    @Test
    void turtleMove() {
        myTurtle.moveForward(10);
        assertEquals(9, myTurtle.getColumnCoordinate());
    }

    @Test
    void changeTurtleDirection() {
        assertEquals(Orientation.COLUMN_RIGHT, myTurtle.getTurtleOrientation());
        myTurtle.setTurtleOrientation(Orientation.COLUMN_LEFT);
        assertEquals(Orientation.COLUMN_LEFT, myTurtle.getTurtleOrientation());
    }

    @Test
    void changeTurtlePenPosition() {
        assertEquals(Position.UP, myTurtle.getMyTurtlePen().getPenPosition());
        biro.setPenPosition(Position.DOWN);
        assertEquals(Position.DOWN, myTurtle.getMyTurtlePen().getPenPosition());
    }

    @Test
    void drawMovingForwardOnSketchPad() {
        myTurtle.getMyTurtlePen().setPenPosition(Position.DOWN);
        myTurtle.setColumnCoordinate(8);
        myTurtle.setRowCoordinate(13);
        try {
            myTurtle.draw(12, aPad);
        }catch (TurtleException turtleEx){
            turtleEx.printStackTrace();
        }

        assertEquals(13, myTurtle.getRowCoordinate());
        assertEquals(19, myTurtle.getColumnCoordinate());
        for (int i = 8; i < 19; i++){
            assertEquals(1, aPad.getFloor()[13][i]);
        }

        myTurtle.setTurtleOrientation(Orientation.ROW_LEFT);
        try {
            myTurtle.draw(12, aPad);
        }catch (TurtleException turtleEx){
            turtleEx.printStackTrace();
        }
        assertEquals(2, myTurtle.getRowCoordinate());
        assertEquals(19, myTurtle.getColumnCoordinate());
        for (int i = 3; i < 14; i++){
            assertEquals(1, aPad.getFloor()[i][19]);
        }

        myTurtle.setTurtleOrientation(Orientation.COLUMN_LEFT);
        try {
            myTurtle.draw(12, aPad);
        }catch (TurtleException turtleEx){
            turtleEx.printStackTrace();
        }
        assertEquals(2, myTurtle.getRowCoordinate());
        assertEquals(8, myTurtle.getColumnCoordinate());
        for (int i = 9; i < 20; i++){
            assertEquals(1, aPad.getFloor()[2][i]);
        }

        myTurtle.setTurtleOrientation(Orientation.ROW_RIGHT);
        try {
            myTurtle.draw(12, aPad);
        }catch (TurtleException turtleEx){
            turtleEx.printStackTrace();
        }
        assertEquals(13, myTurtle.getRowCoordinate());
        assertEquals(8, myTurtle.getColumnCoordinate());
        for (int i = 2; i < 13; i++){
            assertEquals(1, aPad.getFloor()[i][8]);
        }

        for (int i = 0; i < aPad.getFloor().length; i++){
            for (int j = 0; j < aPad.getFloor().length; j++){
                if (aPad.getFloor()[i][j] == 0){
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
    void drawWithTurtlePenUpTest() {
        myTurtle.getMyTurtlePen().setPenPosition(Position.UP);
        try {
            myTurtle.draw(12, aPad);
        }catch (TurtleException turtleEx){
            turtleEx.printStackTrace();
        }

        assertEquals(0, myTurtle.getRowCoordinate());
        assertEquals(11, myTurtle.getColumnCoordinate());
        for (int i = 0; i < 12; i++){
            assertNotEquals(1, aPad.getFloor()[0][i]);
        }
    }

    @Test
    void drawWithZeroStepsTest() {
        Assertions.assertThrows(TurtleException.class, () -> {
            myTurtle.draw(0, aPad);
        });
    }

    @Test
    void drawWithNegativeStepsTest() {
        Assertions.assertThrows(TurtleException.class, () -> {
            myTurtle.draw(-193, aPad);
        });
    }

    @Test
    void drawWithNumberOfStepsGreaterThanSketchPadSizeTest() {
        Assertions.assertThrows(TurtleException.class, () -> {
            myTurtle.draw(193, aPad);
        });
    }

    @Test
    void drawWithZeroSketchPadTest() {
        try {
            myTurtle.draw(12, null);
        } catch (TurtleException e) {
            e.printStackTrace();
        }
    }

    @Test
    void drawWithNumberOfStepsGreaterThanSketchPadColumnTest() {
        myTurtle.setColumnCoordinate(13);
        myTurtle.setTurtleOrientation(Orientation.COLUMN_RIGHT);
        Assertions.assertThrows(TurtleException.class, () -> {
            myTurtle.draw(12, aPad);
        });
    }

    @Test
    void drawWithNumberOfStepsGreaterThanSketchPadRowTest() {
        myTurtle.setRowCoordinate(13);
        myTurtle.setTurtleOrientation(Orientation.ROW_RIGHT);
        Assertions.assertThrows(TurtleException.class, () -> {
            myTurtle.draw(12, aPad);
        });
    }
}
