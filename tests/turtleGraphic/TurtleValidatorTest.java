package turtleGraphic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TurtleValidatorTest {

    private TurtleValidator turtleValidator;

    @BeforeEach
    void setUp() throws Exception{
        turtleValidator = new TurtleValidator();
    }

    @Test
    void turtleValidatorExistsTest() {
        assertNotNull(turtleValidator);
    }

    @Test
    void validateNumberOfStepsColumnRightWithValidSteps() {
        boolean result = TurtleValidator.isValidNumberOfSteps(12, 20, 0, Orientation.COLUMN_RIGHT);
        assertTrue(result);
    }

    @Test
    void validateNumberOfStepsColumnRightWithInvalidSteps() {
        boolean result = TurtleValidator.isValidNumberOfSteps(12, 20, 13, Orientation.COLUMN_RIGHT);
        assertFalse(result);
    }

    @Test
    void validateNumberOfStepsRowRightWithValidSteps() {
        boolean result = TurtleValidator.isValidNumberOfSteps(12, 20, 0, Orientation.ROW_RIGHT);
        assertTrue(result);
    }

    @Test
    void validateNumberOfStepsRowRightWithInvalidSteps() {
        boolean result = TurtleValidator.isValidNumberOfSteps(12, 20, 10, Orientation.ROW_RIGHT);
        assertFalse(result);
    }

    @Test
    void validateNumberOfStepsColumnLeftWithValidSteps() {
        boolean result = TurtleValidator.isValidNumberOfSteps(12, 20, 14, Orientation.COLUMN_LEFT);
        assertTrue(result);
    }

    @Test
    void validateNumberOfStepsColumnLeftWithInvalidSteps() {
        boolean result = TurtleValidator.isValidNumberOfSteps(12, 20, 0, Orientation.COLUMN_LEFT);
        assertFalse(result);
    }

    @Test
    void validateNumberOfStepsRowLeftWithValidSteps() {
        boolean result = TurtleValidator.isValidNumberOfSteps(12, 20, 14, Orientation.ROW_LEFT);
        assertTrue(result);
    }

    @Test
    void validateNumberOfStepsRowLeftWithInvalidSteps() {
        boolean result = TurtleValidator.isValidNumberOfSteps(12, 20, 0, Orientation.ROW_LEFT);
        assertFalse(result);
    }
}
