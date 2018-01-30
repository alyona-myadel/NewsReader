package by.myadel.commands;

import by.myadel.AppContext;
import by.myadel.UserInput;

/**
 * Класс задающий url новостей.
 */
public class ChangeNewsSourceCommand extends Command {
    private static final String LINK_JSON = "http://kiparo.ru/t/it_news.json";
    private static final String LINK_XML = "http://kiparo.ru/t/it_news.xml";

    public ChangeNewsSourceCommand(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void execute() {
        while (true) {
            getAppContext().println("Enter news source(\"xml\", \"json\" or custom url)");
            UserInput userInput = getAppContext().getUserInput();
            if (userInput.getInput().length < 1) {
                getAppContext().println("You must provide news source");
                continue;
            }
            switch (userInput.getInput()[0]) {
                case "xml":
                    getAppContext().setSourceUrl(LINK_XML);
                    return;
                case "json":
                    getAppContext().setSourceUrl(LINK_JSON);
                    return;
                default:
                    getAppContext().setSourceUrl(userInput.getInput()[0]);
                    return;
            }
        }

    }
}
