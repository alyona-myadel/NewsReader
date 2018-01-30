package by.myadel.parsers;

import by.myadel.parsers.json.JsonParserFactory;
import by.myadel.parsers.xml.XmlParserFactory;

public abstract class ParserFactory {
    public static Parser getParserForUrl(String url) throws ParserNotFoundException {
        ParserFactory[] parserFactories = new ParserFactory[]{new XmlParserFactory(), new JsonParserFactory()};
        for (ParserFactory factory : parserFactories) {
            if (factory.canHandleContentType(url)) {
                return factory.buildParser();
            }
        }
        throw new ParserNotFoundException();
    }

    protected abstract Parser buildParser();

    protected abstract boolean canHandleContentType(String url);
}
