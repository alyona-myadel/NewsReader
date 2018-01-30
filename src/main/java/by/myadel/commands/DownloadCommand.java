package by.myadel.commands;

import by.myadel.AppContext;
import by.myadel.EventManager;

/**
 * Класс, отправляющий событие для начала скачивания.
 */
public class DownloadCommand extends Command {

    public DownloadCommand(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void execute() {
        getAppContext().getEventManager().emitEvent(EventManager.START_DOWNLOAD_REQUESTED_EVENT, getAppContext().getSourceUrl());
    }
}
