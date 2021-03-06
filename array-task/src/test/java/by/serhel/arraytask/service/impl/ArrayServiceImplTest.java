package by.serhel.arraytask.service.impl;

import by.serhel.arraytask.entity.Array;
import by.serhel.arraytask.exception.ArrayException;
import by.serhel.arraytask.service.ArrayService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArrayServiceImplTest {
    private Array array;
    private ArrayService arrayService;

    @BeforeClass
    public void setUp() throws ArrayException {
        this.array = new Array(new int[]{-1, 10, 8, 15, -3, -18});
        this.arrayService = new ArrayServiceImpl();
    }

    @Test
    public void testAvg() throws ArrayException {
        int expected = 1;
        int actual = arrayService.avg(array);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testAvgException() throws ArrayException {
        arrayService.avg(null);
    }

    @Test
    public void testSum() throws ArrayException {
        int expected = 11;
        int actual = arrayService.sum(array);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testSumException() throws ArrayException {
        arrayService.sum(null);
    }

    @Test
    public void testCountPositiveNumbers() throws ArrayException {
        int expected = 3;
        int actual = arrayService.countPositiveNumbers(array);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testCountPositiveNumbersException() throws ArrayException {
        arrayService.countPositiveNumbers(null);
    }

    @Test
    public void testCountNegativeNumbers() throws ArrayException {
        int expected = 3;
        int actual = arrayService.countNegativeNumbers(array);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testCountNegativeNumbersException() throws ArrayException {
        arrayService.countNegativeNumbers(null);
    }

    @Test
    public void testMaxValue() throws ArrayException {
        int expected = 15;
        int actual = arrayService.maxValue(array);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testMaxValueException() throws ArrayException {
        arrayService.maxValue(null);
    }

    @Test
    public void testMinValue() throws ArrayException {
        int expected = -18;
        int actual = arrayService.minValue(array);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testMinValueException() throws ArrayException {
        arrayService.minValue(null);
    }

    @Test
    public void testReplaceElementsByCondition() throws ArrayException {
        Array expected = new Array(new int[]{-1, 999, 8, 15, 999, 999});
        Array actual = arrayService.replaceElementsByCondition(array, 999, (value -> value < -1 || value == 10));
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testReplaceElementsByConditionException1() throws ArrayException {
        arrayService.replaceElementsByCondition(null, 15, value -> true);
    }

    @Test(expectedExceptions = ArrayException.class)
    public void testReplaceElementsByConditionException2() throws ArrayException {
        arrayService.replaceElementsByCondition(array, 15, null);
    }
}