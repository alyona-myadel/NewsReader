package by.myadel.parsers.json;

import by.myadel.parsers.Parser;
import by.myadel.parsers.ParserFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParserFactory extends ParserFactory {
    @Override
    public Parser buildParser() {
        return new JsonParser();
    }

    @Override
    public boolean canHandleContentType(String url) {
        Pattern pattern = Pattern.compile("json$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }
}
