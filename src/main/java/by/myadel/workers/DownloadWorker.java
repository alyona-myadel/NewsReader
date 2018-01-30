package by.myadel.workers;

import by.myadel.EventManager;
import by.myadel.downloders.*;

/**
 * Класс, скачивающий файл по событию START_DOWNLOAD_REQUESTED_EVENT.
 */
public class DownloadWorker extends AbstractWorker implements EventManager.Listener, DownloaderCallback {
    public DownloadWorker(EventManager eventManager) {
        super(eventManager);
        getEventManager().subscribeToEvent(EventManager.START_DOWNLOAD_REQUESTED_EVENT, this);
    }

    /**
     * Метод, получающий событие о начале загрузки файла.
     *
     * @param eventName событие START_DOWNLOAD_REQUESTED_EVENT
     * @param data      url файла
     */
    @Override
    public void onEvent(String eventName, Object data) {
        switch (eventName) {
            case EventManager.START_DOWNLOAD_REQUESTED_EVENT:
                startDownload((String) data);
        }
    }

    /**
     * Метод, получающий событие об успешной загрузке файла.
     *
     * @param result результат скачивания.
     */
    @Override
    public void onSuccess(DownloadResult result) {
        getEventManager().emitEvent(EventManager.DOWNLOAD_SUCCESSFUL_EVENT, result);
    }

    /**
     * Метод, получающий событие об неуспешной загрузке файла.
     *
     * @param error произошедшая ощибка.
     */
    @Override
    public void onError(DownloadException error) {
        getEventManager().emitEvent(EventManager.DOWNLOAD_UNSUCCESSFUL_EVENT, error);
    }

    private void startDownload(String url) {
        try {
            Downloader downloader = DownloaderFactory.getDownloaderForUrl(url);
            downloader.download(url, this);
            System.out.println();
        } catch (DownloaderNotFoundException e) {
            onError(null);
        }
    }
}
