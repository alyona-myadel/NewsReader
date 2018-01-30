package by.myadel.downloders.http;

class HttpErrorException extends Exception {
    private int responseCode;

    public HttpErrorException(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
