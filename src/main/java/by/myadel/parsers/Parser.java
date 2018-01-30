package by.myadel.parsers;

public interface Parser {
    void parse(byte[] bytes, ParserCallback callback);
}
