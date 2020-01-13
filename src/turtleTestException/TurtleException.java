package turtleTestException;

public class TurtleException extends Exception{

    public TurtleException(String message) {
        super(message);
    }

    public TurtleException(String message, Exception cause) {
        super(message, cause);
    }
}
