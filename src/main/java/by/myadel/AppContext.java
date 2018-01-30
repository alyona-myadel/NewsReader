package by.myadel;

import by.myadel.data.NewsStorage;
import by.myadel.workers.DownloadWorker;
import by.myadel.workers.ParseWorker;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Класс, хранящий текущее состояние приложения.
 */
public class AppContext implements EventManager.Listener {
    /**
     * Объект хранящий все данные новостей.
     */
    private NewsStorage newsStorage = new NewsStorage();
    /**
     * флаг, показывающий были ли загружены новости
     */
    private boolean isNewsDownloaded = false;
    /**
     * Объект, начинающий скачивание файла по событию.
     */
    private DownloadWorker downloadWorker;
    /**
     * Объект, начинающий парсинг файла пособытию.
     */
    private ParseWorker parseWorker;
    /**
     * url файла
     */
    private String sourceUrl;
    /**
     * Объект, используемый для вывода информации.
     */
    private PrintStream output;
    /**
     * Объект, используемый для ввода информации.
     */
    private InputStream input;
    /**
     * Объект, используемый для парсинга ввода пользователя.
     */
    private Scanner inputScanner;
    /**
     * Объект, получающий оповещение об завершении программы.
     */
    private TerminateAppListener terminateAppListener;
    /**
     * Объект, служащий для передачи событий между частями приложения.
     */
    private EventManager eventManager = new EventManager();
    public AppContext(InputStream input, PrintStream output, TerminateAppListener terminateAppListener) {
        this.input = input;
        this.output = output;
        inputScanner = new Scanner(input);
        this.terminateAppListener = terminateAppListener;
        downloadWorker = new DownloadWorker(eventManager);
        parseWorker = new ParseWorker(eventManager);
        eventManager.subscribeToEvent(EventManager.PARSE_SUCCESSFUL_EVENT, this);
        eventManager.subscribeToEvent(EventManager.PARSE_UNSUCCESSFUL_EVENT, this);
        eventManager.subscribeToEvent(EventManager.DOWNLOAD_UNSUCCESSFUL_EVENT, this);
        eventManager.subscribeToEvent(EventManager.DOWNLOAD_SUCCESSFUL_EVENT, this);

    }

    @Override
    public void onEvent(String eventName, Object data) {
        switch (eventName) {
            case EventManager.DOWNLOAD_SUCCESSFUL_EVENT:
                println("Download successful");
                break;
            case EventManager.PARSE_SUCCESSFUL_EVENT:
                println("Parse successful");
                newsStorage = (NewsStorage) data;
                isNewsDownloaded = true;
                break;
            case EventManager.PARSE_UNSUCCESSFUL_EVENT:
                println("Parse error");
                break;
            case EventManager.DOWNLOAD_UNSUCCESSFUL_EVENT:
                println("Download error");
                break;
        }

    }

    public NewsStorage getNewsStorage() {
        return newsStorage;
    }

    public void setNewsStorage(NewsStorage newsStorage) {
        this.newsStorage = newsStorage;
    }

    public boolean isNewsDownloaded() {
        return isNewsDownloaded;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void println(String text) {
        output.println(text);
    }

    /**
     * Метод, читающий пользовательский ввод.
     *
     * @return объект, содержащий пользовательский ввод.
     */
    public UserInput getUserInput() {
        return new UserInput(inputScanner.nextLine().split(" "));
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public void terminateApp() {
        terminateAppListener.terminateApp();
    }

    public interface TerminateAppListener {
        void terminateApp();
    }
}
