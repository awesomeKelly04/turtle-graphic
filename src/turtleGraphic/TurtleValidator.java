package turtleGraphic;

import java.math.BigInteger;

public class TurtleValidator {

    public static boolean isValidNumberOfSteps(int numberOfSteps, int boundary, int currentCoordinate, Orientation direction) {

        boolean result = false;
        switch (direction){
            case COLUMN_RIGHT:
            case COLUMN_LEFT:
            case ROW_RIGHT:
            case ROW_LEFT:
                result = isValidSteps(numberOfSteps, boundary, currentCoordinate, direction);
                break;
        }
        return result;
    }

    public static boolean isValidNumberOfSteps(int numberOfSteps, int boundary) {
        boolean result = false;
        if (numberOfSteps > BigInteger.ZERO.intValue() && numberOfSteps < boundary){
           result = true;
        }

        return result;
    }

    public static boolean isValidSketchPad(SketchPad thePad) {
        boolean result = true;
        if(thePad == null || thePad.getFloor() == null) {
            result = false;
        }

        return result;
    }

    public static boolean isValidSteps(int numberOfSteps, int boundary, int coordinate, Orientation direction) {
        //check if number of steps make sense regardless of current position
        boolean result = isValidNumberOfSteps(numberOfSteps, boundary);

        switch (direction){
            case COLUMN_RIGHT:
            case ROW_RIGHT:
                //now check if number of steps makes sense based on current position
                int targetColumnRightLocation = coordinate + numberOfSteps - BigInteger.ONE.intValue();

                if(!result || targetColumnRightLocation > boundary) {
                    result = false;
                }
                break;
            case COLUMN_LEFT:
            case ROW_LEFT:
                int targetColumnLeftLocation = coordinate - numberOfSteps + BigInteger.ONE.intValue();

                if(!result || targetColumnLeftLocation < BigInteger.ZERO.intValue()) {
                    result = false;
                }
                break;
        }

        return result;
    }
}
