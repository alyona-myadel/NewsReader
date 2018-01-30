package by.myadel.commands;

import by.myadel.AppContext;
import by.myadel.UserInput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilterNewsByDayCommand extends Command {
    public FilterNewsByDayCommand(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void execute() {
        getAppContext().println("Enter the date(dd.MM.yyyy)");
        Date from = getValidDateFromUser();
        getAppContext().getNewsStorage().filterNewsByDateRange(from, from);
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
