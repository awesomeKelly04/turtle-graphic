package turtleGraphic;

import turtleTestException.TurtleException;

import java.util.Scanner;

public class TurtleLaunder {

    public static void main(String... args) throws TurtleException {
        Scanner store = new Scanner(System.in);
        int commandValue;
        TurtleCommandProcessor turtleCommandProcessor = new TurtleCommandProcessor(new Turtle(), new SketchPad());

        System.out.println();
        System.out.println("---------- Turtle Graphics ----------");
        System.out.println("All commands:");
        // print all commands
        for (Command command : Command.values())
            System.out.printf("%-10s\t\t%-45s%n", command, command.getValue());

        System.out.println("\nEnter turtle commands: ");
        while (true) {
            try {
                commandValue = store.nextInt();
                if (commandValue == Command.MOVE_FORWARD.getValue()){
                    System.out.println("Enter number of steps turtle should take: ");
                    int numberOfSteps = store.nextInt();
                    turtleCommandProcessor.setNumberOfSteps(numberOfSteps);
                }
                turtleCommandProcessor.processUserCommand(commandValue);
            } catch (TurtleException e) {
                e.getMessage();
            }
        }
    }
}
