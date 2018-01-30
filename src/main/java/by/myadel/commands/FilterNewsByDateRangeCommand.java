package by.myadel.commands;

import by.myadel.AppContext;
import by.myadel.UserInput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс, фильтрующий новости по диапазону дат.
 */
public class FilterNewsByDateRangeCommand extends Command {

    public FilterNewsByDateRangeCommand(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void execute() {
        getAppContext().println("Enter date from (dd.MM.yyyy)");
        Date from = getValidDateFromUser();
        getAppContext().println("Enter date to (dd.MM.yyyy)");
        Date to = getValidDateFromUser();
        getAppContext().getNewsStorage().filterNewsByDateRange(from, to);
    }

    private Date getValidDateFromUser() {
        while (true) {
            try {
                return tryParseDate();
            } catch (ParseException e) {
                getAppContext().println("Please enter valid date (dd.MM.yyyy)");
            }
        }
    }

    private Date tryParseDate() throws ParseException {
        UserInput userInput = getAppContext().getUserInput();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.parse(userInput.getLine());
    }
}
