package by.myadel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Класс, передающий события между разными частями приложения.
 */
public class EventManager {
    public static final String DOWNLOAD_SUCCESSFUL_EVENT = "download_successful";
    public static final String DOWNLOAD_UNSUCCESSFUL_EVENT = "download_unsuccessful";
    public static final String START_DOWNLOAD_REQUESTED_EVENT = "start_download_requested";
    public static final String PARSE_SUCCESSFUL_EVENT = "parse_successful";
    public static final String PARSE_UNSUCCESSFUL_EVENT = "parse_unsuccessful";
    private Map<String, List<Listener>> listeners = new HashMap<>();

    /**
     * Метод, подписки на определенные события.
     *
     * @param eventName имя события.
     * @param listener  объект-обработчик события.
     */
    public synchronized void subscribeToEvent(String eventName, Listener listener) {
        if (listeners.containsKey(eventName)) {
            listeners.get(eventName).add(listener);
        } else {
            List<Listener> listeners = new LinkedList<>();
            listeners.add(listener);
            this.listeners.put(eventName, listeners);
        }
    }

    /**
     * Метод, отписки от определенного события.
     *
     * @param eventName имя события.
     * @param listener  объект-обработчик события.
     */
    public synchronized void unsubscribeFromEvent(String eventName, Listener listener) {
        if (listeners.containsKey(eventName)) {
            List<Listener> eventSubscribers = listeners.get(eventName);
            for (int i = 0; i < eventSubscribers.size(); ++i) {
                if (eventSubscribers.get(i) == listener) {
                    eventSubscribers.remove(i);
                    --i;
                }
            }
        }
    }

    /**
     * Метод, инициирующий событие.
     *
     * @param eventName имя события.
     * @param data      данные привязанные к событию.
     */
    public synchronized void emitEvent(String eventName, Object data) {
        if (listeners.containsKey(eventName)) {
            for (Listener listener : listeners.get(eventName)) {
                listener.onEvent(eventName, data);
            }
        }
    }

    public interface Listener {
        void onEvent(String eventName, Object data);
    }
}
