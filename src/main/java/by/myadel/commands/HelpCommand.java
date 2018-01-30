package by.myadel.commands;

import by.myadel.AppContext;

import java.util.Map;

/**
 * Класс, выводящий справку.
 */
public class HelpCommand extends Command {
    private static final int COMMAND_COLUMN_LENGTH = 21;
    private static final Map<String, String> commands = Map.of(
            "help", "prints this help",
            "fetch", "fetch news from specified source",
            "print_all", "prints all news",
            "filter_by_date_range", "prints all news in specified date range",
            "filter_by_day", "prints all news for a certain date",
            "sort_all", "sort news by date",
            "search_by_content", "search phrase in news",
            "search_by_keyword", "print all news with specified keyword",
            "exit", "closes application"
    );

    public HelpCommand(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void execute() {
        getAppContext().println("Available commands:");
        for (Map.Entry<String, String> command : commands.entrySet()) {
            getAppContext().println(generateCommandHelp(command.getKey(), command.getValue()));
        }
    }

    private String generateCommandHelp(String commandName, String commandDescription) {
        StringBuilder builder = new StringBuilder();
        builder.append(commandName);
        for (int i = 0; i < COMMAND_COLUMN_LENGTH - commandName.length(); ++i) {
            builder.append(" ");
        }
        builder.append(commandDescription);
        return builder.toString();
    }
}
