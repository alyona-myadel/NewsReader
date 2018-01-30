package by.myadel.commands;

import by.myadel.AppContext;
import by.myadel.UserInput;

/**
 * Класс, сортирующий новости по возрастанию или убыванию дат.
 */
public class SortNewsByDateCommand extends Command {
    public SortNewsByDateCommand(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void execute() {
        getAppContext().println("Enter news sort(\"ascending\", \"descending\")");
        while (true) {
            switch (tryEnterSortType()) {
                case "ascending":
                    getAppContext().getNewsStorage().sortNewsByAscendingDate();
                    return;
                case "descending":
                    getAppContext().getNewsStorage().sortNewsByDescendingDate();
                    return;
                default:
                    getAppContext().println("Please, enter news sort(\"ascending\", \"descending\")");
                    break;
            }
        }

    }

    private String tryEnterSortType() {
        UserInput userInput = getAppContext().getUserInput();
        return userInput.getLine();
    }
}
