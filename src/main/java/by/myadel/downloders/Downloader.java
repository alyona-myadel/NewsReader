package by.myadel.downloders;


public interface Downloader {
    void download(String url, DownloaderCallback callback);
}
