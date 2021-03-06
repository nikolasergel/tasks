package by.serhel.xmlparsing.builder;

import by.serhel.xmlparsing.entity.Candy;
import by.serhel.xmlparsing.entity.ChocolateCandy;
import by.serhel.xmlparsing.exception.CustomParseXmlException;
import by.serhel.xmlparsing.exception.ResourceNotFoundException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCandyBuilder {
    protected Set<Candy> candies;
    protected Set<ChocolateCandy> chocolateCandies;

    public AbstractCandyBuilder() {
        this.candies = new HashSet<>();
        this.chocolateCandies = new HashSet<>();
    }

    public abstract void build(String filePath) throws CustomParseXmlException, ResourceNotFoundException;

    public Set<Candy> getCandies() {
        return candies;
    }

    public Set<ChocolateCandy> getChocolateCandies() {
        return chocolateCandies;
    }
}
