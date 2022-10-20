package MusicApp.ConsoleSystem;

import MusicApp.helpers.InputReader;

import java.util.*;
import java.util.function.Consumer;

public class ConsoleSystem {
    /**
     * The map of all registered commands, with the name of the command as the key
     */
    private static final Map<String, Command> commands = new HashMap<>();

    /**
     * Used within the main loop to determine whether the application should continue running
     */
    private static boolean wantsToExit = false;

    /**
     * Registers a new command to the console system
     * @param command The command to register
     * @param description The description of the command, this is used when the user types help
     * @param function The function to run when the command is called
     */
    public static void registerCommand(String command, String description, Consumer<String[]> function) {
        commands.put(command, new Command(command, description, function));
    }

    /**
     * Starts the console system, and starts listening for commands
     */
    public static void start() {
        // Store the user's input
        String input = "";

        // Display a welcome message
        System.out.println("Welcome to the Music Application!");
        System.out.println("*********************************");
        System.out.println("Type 'help' to get a list of commands");
        System.out.println("");

        // Loop until the user wants to exit
        while(!wantsToExit) {
            // Get the console input, and check if it's a registered command
            input = InputReader.getString("");

            // First, split the input into an array of strings (split by spaces)
            String[] inputArray = input.split(" ");

            // Then, get the first string in the array (the command)
            String command = inputArray[0];

            // Check if the command is registered
            if(commands.containsKey(command)) {
                // If it is, run the command
                commands.get(command).getAction().accept(inputArray);
            } else {
                // If it isn't, print an error message
                System.out.println("Command not found!");
            }
        }
    }

    /**
     * Sets the wantsToExit variable to true, which will cause the console system to exit
     */
    public static void exit() {
        wantsToExit = true;
    }

    /**
     * Gets all registered commands
     * @return A list of all registered commands
     */
    public static Collection<Command> getCommands() {
        return commands.values();
    }

    /**
     * Returns a command from the dictionary, or null if it doesn't exist
     * @param commandName The name of the command
     * @return The command, or null if it doesn't exist
     */
    public static Command getCommand(String commandName) {
        // Check if the command is registered
        if(commands.containsKey(commandName)) {
            // If it is, return the command
            return commands.get(commandName);
        }

        // Return null if the command isn't registered
        return null;
    }
}
