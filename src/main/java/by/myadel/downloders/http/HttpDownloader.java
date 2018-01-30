package by.myadel.downloders.http;

import by.myadel.downloders.DownloadException;
import by.myadel.downloders.DownloadResult;
import by.myadel.downloders.Downloader;
import by.myadel.downloders.DownloaderCallback;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Класс, позволяющий скачивать данные по заданному url в байтовый массив.
 */
public class HttpDownloader implements Downloader {
    /**
     * Скачивает файл по указанному url в другом потоке.
     *
     * @param url      url файла с новостями
     * @param callback объект с помощью которого скачивающий поток оповещяет внешний код
     *                 об успешном или неуспешном скачивании.
     */
    public void download(String url, DownloaderCallback callback) {
        DownloaderRunnable runnable = new DownloaderRunnable(url, callback);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private class DownloaderRunnable implements Runnable {
        private String url;
        private DownloaderCallback callback;

        DownloaderRunnable(String url, DownloaderCallback callback) {
            this.url = url;
            this.callback = callback;
        }

        @Override
        public void run() {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                if (connection.getResponseCode() != 200) {
                    throw new HttpErrorException(connection.getResponseCode());
                }
                byte[] data = connection.getInputStream().readAllBytes();
                callback.onSuccess(new DownloadResult(url, data));
            } catch (Exception e) {
                callback.onError(new DownloadException(e));
            }

        }
    }
}
