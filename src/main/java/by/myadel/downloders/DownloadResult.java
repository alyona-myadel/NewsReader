package by.myadel.downloders;

public class DownloadResult {
    private String url;
    private byte[] data;

    public DownloadResult(String url, byte[] data) {
        this.url = url;
        this.data = data;
    }

    public String getUrl() {

        return url;
    }

    public byte[] getData() {
        return data;
    }
}
