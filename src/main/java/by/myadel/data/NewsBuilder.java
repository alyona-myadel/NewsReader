package by.myadel.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsBuilder {
    private News news = new News();

    private static Date parseDateFromString(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        Date convertedDate = null;
        try {
            convertedDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public NewsBuilder setDate(Date date) {
        news.setDate(date);
        return this;
    }

    public NewsBuilder setDate(String date) {
        news.setDate(parseDateFromString(date));
        return this;
    }

    public NewsBuilder setDescription(String description) {
        news.setDescription(description);
        return this;
    }

    public NewsBuilder setID(long id) {
        news.setId(id);
        return this;
    }

    public NewsBuilder addKeyword(String keyword) {
        news.addKeyword(keyword);
        return this;
    }

    public NewsBuilder setTitle(String title) {
        news.setTitle(title);
        return this;
    }

    public NewsBuilder setVisible(boolean visible) {
        news.setVisible(visible);
        return this;
    }

    public News build() {
        return news;
    }
}
