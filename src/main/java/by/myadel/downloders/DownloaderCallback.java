package by.myadel.downloders;

public interface DownloaderCallback {

    /**
     * Оповещает об успешном скачивании.
     *
     * @param result результат скачивания.
     */
    void onSuccess(DownloadResult result);

    /**
     * Оповещает об ошибке скачивания.
     *
     * @param error произошедшая ощибка.
     */
    void onError(DownloadException error);
}
