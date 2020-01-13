package turtleTestException;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TurtleExceptionTest {

    private  TurtleException turtleException;

    @BeforeEach
    void setUp() throws Exception {
        turtleException = new  TurtleException("Turtle got an error");
    }

    @Test
    void turtleExceptionExistsTest() {
        assertNotNull(turtleException);
    }

    @Test
    void turtleExceptionExistsWithThrowableTest() {
        TurtleException turtleProblem = new  TurtleException("Turtle got an error", turtleException);
        assertNotNull(turtleProblem);
    }
}
