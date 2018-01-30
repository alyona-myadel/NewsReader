package by.myadel;

import by.myadel.commands.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main implements AppContext.TerminateAppListener {
    private boolean isAppTerminateRequested = false;

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        new Main().start();
    }

    private void start() {
        AppContext appContext = new AppContext(System.in, System.out, this);
        Map<String, Command> availableCommands = new HashMap<>();

        availableCommands.put("help", buildHelpCommand(appContext));
        availableCommands.put("exit", buildExitCommand(appContext));
        availableCommands.put("fetch", buildFetchCommand(appContext));
        availableCommands.put("filter_by_date_range", buildFilterByDateRangeCommand(appContext));
        availableCommands.put("filter_by_day", buildFilterByDayCommand(appContext));
        availableCommands.put("print_all", buildPrintAllCommand(appContext));
        availableCommands.put("sort_all", buildSortAllCommand(appContext));
        availableCommands.put("search_by_content", buildSearchByContentCommand(appContext));
        availableCommands.put("search_by_keyword", buildSearchByKeywordCommand(appContext));

        while (!isAppTerminateRequested) {
            appContext.println("Enter the command (command \"help\" also available)");
            UserInput userInput = appContext.getUserInput();
            Command command = availableCommands.get(userInput.getCommand());
            if (command == null) {
                continue;
            }
            command.execute();
        }
    }

    private Command buildHelpCommand(AppContext appContext) {
        return new HelpCommand(appContext);
    }

    private Command buildExitCommand(AppContext appContext) {
        return new ExitCommand(appContext);
    }

    private Command buildFetchCommand(AppContext appContext) {
        return new AggregatedCommand(appContext, new Command[]{
                new ChangeNewsSourceCommand(appContext),
                new DownloadCommand(appContext)
        });
    }

    private Command buildFilterByDateRangeCommand(AppContext appContext) {
        return printAfterCommandExecute(appContext, new FilterNewsByDateRangeCommand(appContext));
    }

    private Command buildFilterByDayCommand(AppContext appContext) {
        return printAfterCommandExecute(appContext, new FilterNewsByDayCommand(appContext));
    }

    private Command buildPrintAllCommand(AppContext appContext) {
        return printAfterCommandExecute(appContext, new MakeAllNewsVisibleCommand(appContext));
    }

    private Command buildSortAllCommand(AppContext appContext) {
        return printAfterCommandExecute(appContext, new SortNewsByDateCommand(appContext));
    }

    private Command buildSearchByContentCommand(AppContext appContext) {
        return printAfterCommandExecute(appContext, new SearchByContentCommand(appContext));
    }

    private Command buildSearchByKeywordCommand(AppContext appContext) {
        return printAfterCommandExecute(appContext, new SearchByKeywordCommand(appContext));
    }

    private Command printAfterCommandExecute(AppContext appContext, Command command) {
        return new AggregatedCommand(appContext, new Command[]{
                command,
                new PrintNewsCommand(appContext)
        });
    }

    @Override
    public void terminateApp() {
        isAppTerminateRequested = true;
    }
}
