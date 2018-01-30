package by.myadel.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

/**
 * Класс, содержащий новость.
 */
public class News {
    private Date date;
    private String description;
    private long id;
    private TreeSet<String> keywords = new TreeSet<>();
    private String title;
    private boolean visible;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
    }

    public TreeSet<String> getKeywords() {
        return keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        StringBuilder fullNews = new StringBuilder();
        fullNews.append("\n[");
        fullNews.append("\n\tid = '").append(id).append("',");
        fullNews.append("\n\ttitle = '").append(title).append("',");
        fullNews.append("\n\tdate = '").append(new SimpleDateFormat("EEEE d MMMM yyyy hh:mm").format(date));
        fullNews.append("',\n\tdescription = '").append(description).append("', \n\tkeywords: '");
        for (String word : keywords) {
            fullNews.append(word).append(" ");
        }
        fullNews.append("',\n]");
        return fullNews.toString();
    }
}
