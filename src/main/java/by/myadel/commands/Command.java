package by.myadel.commands;

import by.myadel.AppContext;

public abstract class Command {
    private AppContext appContext;

    Command(AppContext appContext) {
        this.appContext = appContext;
    }

    AppContext getAppContext() {
        return appContext;
    }

    public abstract void execute();
}
