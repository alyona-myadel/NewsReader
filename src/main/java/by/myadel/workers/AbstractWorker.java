package by.myadel.workers;

import by.myadel.EventManager;

abstract class AbstractWorker {
    private EventManager eventManager;

    AbstractWorker(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    EventManager getEventManager() {
        return eventManager;
    }
}
