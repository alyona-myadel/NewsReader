package by.myadel.parsers.json;

import by.myadel.data.NewsBuilder;
import by.myadel.data.NewsStorage;
import by.myadel.parsers.ParseException;
import by.myadel.parsers.Parser;
import by.myadel.parsers.ParserCallback;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Класс, позволяющий парсить байтовый массив, с помощью библиотеки GSON.
 */
public class JsonParser implements Parser {
    private NewsStorage newsStorage = new NewsStorage();

    /**
     * Метод, позволяющий парсить байтовый массив в другом потоке.
     *
     * @param bytes    байтовый массив
     * @param callback объект с помощью которого другой поток оповещяет внешний код
     *                 об успешном или неуспешном парсинге.
     */
    public void parse(byte[] bytes, ParserCallback callback) {
        JsonParserRunnable runnable = new JsonParserRunnable(bytes, callback);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private class JsonParserRunnable implements Runnable {
        private byte[] bytes;
        private ParserCallback callback;

        JsonParserRunnable(byte[] bytes, ParserCallback callback) {
            this.bytes = bytes;
            this.callback = callback;
        }

        @Override
        public void run() {
            try {
                com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
                Object object = parser.parse(new String(bytes));
                JsonObject jsonObject = (JsonObject) object;
                newsStorage.setLocation(jsonObject.get("location").getAsString());
                newsStorage.setName(jsonObject.get("name").getAsString());
                JsonArray news = jsonObject.getAsJsonArray("news");
                for (JsonElement elementNews : news) {
                    JsonObject elementObj = (JsonObject) elementNews;
                    NewsBuilder newsBuilder = new NewsBuilder();
                    newsBuilder.setID(elementObj.get("id").getAsLong());
                    newsBuilder.setDate(elementObj.get("date").getAsString());
                    if (elementObj.has("title")) {
                        newsBuilder.setTitle(elementObj.get("title").getAsString());
                    }
                    newsBuilder.setDescription(elementObj.get("description").getAsString());
                    JsonArray keywords = elementObj.get("keywords").getAsJsonArray();
                    if (keywords.size() > 0) {
                        for (JsonElement keyword : keywords) {
                            newsBuilder.addKeyword(keyword.getAsString());
                        }
                    }
                    newsBuilder.setVisible(elementObj.get("visible").getAsBoolean());
                    newsStorage.setNewsList(newsBuilder.build());
                }
                callback.onSuccess(newsStorage);
            } catch (Exception e) {
                callback.onError(new ParseException(e));
            }
        }
    }
}


