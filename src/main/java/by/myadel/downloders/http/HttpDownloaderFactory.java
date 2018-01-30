package by.myadel.downloders.http;

import by.myadel.downloders.Downloader;
import by.myadel.downloders.DownloaderFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpDownloaderFactory extends DownloaderFactory {

    /**
     * Создает и возвращает объект класса HTTPDownloader.
     *
     * @return новый объект HttpDownloader
     */
    @Override
    public Downloader buildDownloader() {
        return new HttpDownloader();
    }

    /**
     * Проверяет сможет ли HttpDownloader скачать данные по заданному url.
     *
     * @param url url файла с новостями
     * @return true - если url может быть обслужен HTTPDownloader, иначе - false.
     */
    @Override
    public boolean canHandleURL(String url) {
        Pattern pattern = Pattern.compile("^(http|https)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }
}
