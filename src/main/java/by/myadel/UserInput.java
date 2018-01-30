package by.myadel;

/**
 * Класс, хранящий пользовательский ввод.
 */
public class UserInput {
    private String[] params;

    public UserInput(String[] params) {
        this.params = params;
    }

    public String[] getInput() {
        return params;
    }

    public String getCommand() {
        if (params.length == 0) {
            return "";
        }
        return params[0];
    }

    public String getLine() {
        return String.join("", params);
    }
}
