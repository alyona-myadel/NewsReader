package by.myadel.parsers;

import by.myadel.data.NewsStorage;

public interface ParserCallback {
    void onSuccess(NewsStorage storage);

    void onError(Throwable error);
}
