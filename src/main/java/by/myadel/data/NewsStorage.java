package by.myadel.data;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

/**
 * Класс, содержащий все новости.
 */
public class NewsStorage {
    private String location;
    private String name;
    private LinkedList<News> newsList = new LinkedList<>();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(News newsList) {
        this.newsList.add(newsList);
    }

    @Override
    public String toString() {
        StringBuilder newsStorage = new StringBuilder("File{ \nlocation = '").append(location);
        newsStorage.append("', \nname = '").append(name).append("', \nNews{ ");
        for (News news : newsList) {
            if (news.isVisible()) {
                newsStorage.append(news.toString());
            }
        }
        newsStorage.append("\n}");
        return newsStorage.toString();
    }

    /**
     * Метод, позволяющий сделать все новости видимыми.
     */
    public void makeAllNewsVisible() {
        for (News news : newsList) {
            news.setVisible(true);
        }
    }

    /**
     * Фильтрует список новостей по введенному диапазону дат.
     *
     * @param from дата начала промежутка, включительно.
     * @param to   дата конца промежутка, включительно.
     */
    public void filterNewsByDateRange(Date from, Date to) {
        if (from.equals(to)) {
            to = addOneDayToDate(to);
        }
        for (News news : newsList) {
            news.setVisible(news.getDate().equals(from) || (news.getDate().after(from) && news.getDate().before(to)));
        }
    }

    private Date addOneDayToDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }

    /**
     * Сортирует список новостей по возрастанию дат.
     */
    public void sortNewsByAscendingDate() {
        sortNewsByDate(true);
    }

    /**
     * Сортирует список новостей по убыванию дат.
     */
    public void sortNewsByDescendingDate() {
        sortNewsByDate(false);
    }

    private void sortNewsByDate(boolean isAscendingSort) {
        newsList.sort((object1, object2) -> {
            int compareResult = object1.getDate().compareTo(object2.getDate());
            return isAscendingSort ? compareResult : -compareResult;
        });
    }

    /**
     * Поиск новостей по введенному слову или фразе.
     */
    public void wordSearch(String word) {
        for (News news : newsList) {
            if (checkWordInText(news.getTitle(), word) || checkWordInText(news.getDescription(), word)) {
                news.setVisible(true);
            } else {
                news.setVisible(false);
            }
        }

    }

    private boolean checkWordInText(String text, String word) {
        Pattern p = Pattern.compile(".*" + word + ".*", CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        return m.matches();
    }

    /**
     * Получить множество ключевых слов из этого списка новостей.
     *
     * @return множество ключевых слов
     */
    public Set<String> getKeywordsSet() {
        TreeSet<String> availableKeywords = new TreeSet<>();
        for (News news : newsList) {
            availableKeywords.addAll(news.getKeywords());
        }
        return availableKeywords;
    }

    /**
     * Фильтрация списка новостей по ключевому слову.
     */
    public void filterNewsByKeyword(String keyword) {
        for (News news : newsList) {
            news.setVisible(news.getKeywords().contains(keyword));
        }
    }
}
