package by.myadel.commands;

import by.myadel.AppContext;

public class MakeAllNewsVisibleCommand extends Command {
    public MakeAllNewsVisibleCommand(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void execute() {
        getAppContext().getNewsStorage().makeAllNewsVisible();
    }
}
