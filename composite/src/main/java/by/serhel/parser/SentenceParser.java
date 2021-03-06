package by.serhel.parser;

import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;

public class SentenceParser extends AbstractParser {
    public static String SENTENCE_REGEX = "(?<=[.!?]\\s)(?=[A-Z])";

    public SentenceParser(AbstractParser next) {
        super(next);
    }

    @Override
    public void parse(String text, TextElement element) {
        String[] parts = text.split(SENTENCE_REGEX);

        for (String part : parts) {
            TextElement sentence = new TextElement(TextElementType.SENTENCE);
            element.add(sentence);
            next.parse(part, sentence);
        }
    }
}
