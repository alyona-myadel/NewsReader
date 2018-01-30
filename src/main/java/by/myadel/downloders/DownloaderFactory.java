package by.myadel.downloders;

import by.myadel.downloders.http.HttpDownloaderFactory;

public abstract class DownloaderFactory {
    public static Downloader getDownloaderForUrl(String url) throws DownloaderNotFoundException {
        DownloaderFactory[] downloaderFactories = new DownloaderFactory[]{new HttpDownloaderFactory()};
        for (DownloaderFactory factory : downloaderFactories) {
            if (factory.canHandleURL(url)) {
                return factory.buildDownloader();
            }
        }
        throw new DownloaderNotFoundException();
    }

    protected abstract Downloader buildDownloader();

    protected abstract boolean canHandleURL(String url);
}
