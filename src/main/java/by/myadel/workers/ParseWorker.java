package by.myadel.workers;

import by.myadel.EventManager;
import by.myadel.data.NewsStorage;
import by.myadel.downloders.DownloadResult;
import by.myadel.parsers.*;

/**
 * Класс, запускающий парсинг файла после события DOWNLOAD_SUCCESSFUL_EVENT.
 */
public class ParseWorker extends AbstractWorker implements EventManager.Listener, ParserCallback {

    public ParseWorker(EventManager eventManager) {
        super(eventManager);
        getEventManager().subscribeToEvent(EventManager.DOWNLOAD_SUCCESSFUL_EVENT, this);
    }

    /**
     * Метод, запускающий парсер после успешного скачивания.
     *
     * @param eventName
     * @param data
     */
    @Override
    public void onEvent(String eventName, Object data) {
        DownloadResult result = (DownloadResult) data;
        try {
            Parser parser = ParserFactory.getParserForUrl(result.getUrl());
            parser.parse(result.getData(), this);
        } catch (ParserNotFoundException e) {
            onError(new ParseException(e));
        }
    }

    /**
     * Метод, получающий событие об успешном парсинге файла.
     *
     * @param storage объект, хранящий все новости.
     */
    @Override
    public void onSuccess(NewsStorage storage) {
        getEventManager().emitEvent(EventManager.PARSE_SUCCESSFUL_EVENT, storage);
    }

    /**
     * Метод, получающий событие об неуспешном парсинге файла.
     *
     * @param error произошедшая ошибка.
     */
    @Override
    public void onError(Throwable error) {
        getEventManager().emitEvent(EventManager.PARSE_UNSUCCESSFUL_EVENT, error);
    }
}
