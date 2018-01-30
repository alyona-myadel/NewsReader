package by.myadel.parsers.xml;

import by.myadel.parsers.Parser;
import by.myadel.parsers.ParserFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParserFactory extends ParserFactory {
    @Override
    public Parser buildParser() {
        return new XmlParser();
    }

    @Override
    public boolean canHandleContentType(String url) {
        Pattern pattern = Pattern.compile("xml$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }
}
