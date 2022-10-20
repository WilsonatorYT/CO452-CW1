package MusicApp.ConsoleSystem;

import java.util.function.Consumer;

public class Command {
    private String name;
    private String description;
    private Consumer<String[]> action;

    public Command(String name, String description, Consumer<String[]> action) {
        this.name = name;
        this.description = description;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Consumer<String[]> getAction() {
        return action;
    }
}
