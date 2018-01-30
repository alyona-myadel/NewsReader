package by.myadel.commands;

import by.myadel.AppContext;

/**
 * Класс, выводящий все видимые новости.
 */
public class PrintNewsCommand extends Command {
    public PrintNewsCommand(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void execute() {
        if (getAppContext().isNewsDownloaded()) {
            getAppContext().println(getAppContext().getNewsStorage().toString());
        } else {
            getAppContext().println("News not downloaded yet");
        }

    }
}