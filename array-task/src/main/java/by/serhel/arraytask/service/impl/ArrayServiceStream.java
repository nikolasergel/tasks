package by.serhel.arraytask.service.impl;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.service.ArrayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class ArrayServiceStream implements ArrayService {
    private static final Logger logger = LogManager.getLogger();

    public ArrayServiceStream() {

    }

    public int avg(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("maxValue: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int avg = sum(array) / array.getLength();
        return avg;
    }

    public int sum(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("sum: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int sum = Arrays.stream(array.getArray())
                .sum();
        return sum;
    }

    public int countPositiveNumbers(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("countPositiveNumbers: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int countPositive = (int)Arrays.stream(array.getArray())
                .filter(element -> element >= 0)
                .count();
        return countPositive;
    }

    public int countNegativeNumbers(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("countNegativeNumbers: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int countNegative = (int)Arrays.stream(array.getArray())
                .filter(element -> element < 0)
                .count();
        return countNegative;
    }

    public int maxValue(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("maxValue: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int max = Arrays.stream(array.getArray())
                .max()
                .getAsInt();
        return max;
    }

    public int minValue(Array array) throws ArrayException {
        if (array == null) {
            ArrayException exception = new ArrayException("minValue: Array can't be null");
            logger.info(exception);
            throw exception;
        }
        int min = Arrays.stream(array.getArray())
                .min()
                .getAsInt();
        return min;
    }

    @Override
    public Array replaceElementsByCondition(Array array, int value, IntFunction<Boolean> statement) throws ArrayException {
        if (array == null || statement == null) {
            ArrayException exception = new ArrayException("function parameters can't be null!");
            logger.info(exception);
            throw exception;
        }
        Array arrayCopy = new Array(array.getArray());
        IntStream.range(0, arrayCopy.getLength())
                .forEach(index -> {
                    try {
                         if(statement.apply(arrayCopy.getElement(index))){
                                 arrayCopy.setElement(value, index);
                         };
                    } catch (ArrayException arrayException) {
                        logger.error("Incorrect index", arrayException);
                    }
                });
        return arrayCopy;
    }
}
