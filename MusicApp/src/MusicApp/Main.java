package MusicApp;
import MusicApp.ConsoleSystem.ConsoleSystem;

import java.util.Map;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        // Register commands to the console system
        registerCommands();

        // Start the console system
        ConsoleSystem.start();
    }

    /**
     * Moved to a separate method to prevent it from taking up space in the main method
     */
    private static void registerCommands() {
        ConsoleSystem.registerCommand("help", "Lists all of the commands and what they do, or information about a specific command.", (args) -> {
            // if the user has specified a command, print the command's description
            if (args.length == 1) {
                // Get the argument
                String commandName = args[0];

                var command = ConsoleSystem.getCommand(commandName);
            }

            // Get all commands
            var commands = ConsoleSystem.getCommands();
            // Print all commands
            for (var command : commands) {
                System.out.println(command.getName() + " - " + command.getDescription());
            }
        });
        ConsoleSystem.registerCommand("exit", "Exits the application.", (args) -> {
            ConsoleSystem.exit();
        });
    }
}