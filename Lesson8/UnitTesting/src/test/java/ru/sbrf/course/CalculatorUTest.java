package ru.sbrf.course;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorUTest {
    Calculator calculator;
    @Before
    public void Init() {
        calculator = new Calculator();
    }

    @Test
    public void addTest(){
        Assert.assertEquals("2+5 test failed", calculator.add(2,5), 8);
    }
}
