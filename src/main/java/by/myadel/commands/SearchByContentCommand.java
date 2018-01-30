package by.myadel.commands;

import by.myadel.AppContext;
import by.myadel.UserInput;

public class SearchByContentCommand extends Command {
    public SearchByContentCommand(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void execute() {
        getAppContext().println("Search by text, enter the word or words");
        UserInput userInput = getAppContext().getUserInput();
        getAppContext().getNewsStorage().wordSearch(userInput.getLine());
    }
}
