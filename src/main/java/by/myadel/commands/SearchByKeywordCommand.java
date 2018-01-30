package by.myadel.commands;

import by.myadel.AppContext;
import by.myadel.UserInput;

import java.util.Set;

/**
 * Класс, позволяющий искать новоти по ключевому слову.
 */
public class SearchByKeywordCommand extends Command {

    public SearchByKeywordCommand(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void execute() {
        getAppContext().println("Keywords list:");
        Set<String> keywords = getAppContext().getNewsStorage().getKeywordsSet();
        Object[] keywordsArray = keywords.toArray();
        for (int i = 0; i < keywordsArray.length; ++i) {
            getAppContext().println(i + ". " + keywordsArray[i]);
        }
        getAppContext().println("Enter the number of keyword (\"1\")");
        int index = getValidKeywordNumber(keywordsArray.length - 1);
        getAppContext().getNewsStorage().filterNewsByKeyword((String) keywordsArray[index]);
    }

    private int getValidKeywordNumber(int maxValue) {
        while (true) {
            try {
                int keywordNumber = tryEnterKeywordNumber();
                if (keywordNumber > maxValue) {
                    throw new IndexOutOfBoundsException();
                }
                return keywordNumber;
            } catch (Exception e) {
                getAppContext().println("Enter valid number of keyword (\"1\")");
            }
        }
    }

    private int tryEnterKeywordNumber() throws NumberFormatException {
        UserInput userInput = getAppContext().getUserInput();
        return Integer.valueOf(userInput.getLine());
    }
}
