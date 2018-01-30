package by.myadel.commands;

import by.myadel.AppContext;

/**
 * Класс, компанующий несколько команд.
 */
public class AggregatedCommand extends Command {
    private Command[] childCommands;

    public AggregatedCommand(AppContext appContext, Command[] childCommands) {
        super(appContext);
        this.childCommands = childCommands;
    }

    @Override
    public void execute() {
        for (Command command : childCommands) {
            command.execute();
        }
    }
}
