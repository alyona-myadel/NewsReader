package by.myadel.commands;

import by.myadel.AppContext;

/**
 * Класс, прекращающий работу приложения.
 */
public class ExitCommand extends Command {
    public ExitCommand(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void execute() {
        getAppContext().println("Bye-bye");
        getAppContext().terminateApp();
    }
}
