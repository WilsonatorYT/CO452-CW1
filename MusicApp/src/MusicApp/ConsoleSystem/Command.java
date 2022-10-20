package MusicApp.ConsoleSystem;

import java.util.function.Consumer;

public class Command {
    /**
     * The name of the command
     */
    private String name;

    /**
     * The description of the command, this is used when the user types help
     */
    private String description;

    /**
     * The function to run when the command is called
     */
    private Consumer<String[]> action;

    /**
     * Creates a new command
     * @param name The name of the command
     * @param description The description of the command, this is used when the user types help
     * @param action The function to run when the command is called
     */
    public Command(String name, String description, Consumer<String[]> action) {
        this.name = name;
        this.description = description;
        this.action = action;
    }

    /**
     * Gets the name of the command
     * @return The name of the command
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the command
     * @return The description of the command
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the action of the command
     * @return The action of the command
     */
    public Consumer<String[]> getAction() {
        return action;
    }
}
