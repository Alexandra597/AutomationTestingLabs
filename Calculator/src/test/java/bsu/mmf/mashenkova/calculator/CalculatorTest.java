package bsu.mmf.mashenkova.calculator;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void testAddPositives() throws Exception {
        int actual = Calculator.add(111,238);
        int expected = 111 + 238;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddWithNegatives() throws Exception {
        int actual = Calculator.add(-111,238);
        int expected = -111 + 238;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSubtractFromBigger() throws Exception {
        int actual = Calculator.subtract(111, 55);
        int expected = 111 - 55;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSubtractFromSmaller() throws Exception {
        int actual = Calculator.subtract(55,111);
        int expected = 55 - 111;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMultiplyPositives() throws Exception {
        double actual = Calculator.multiply(12, 13);
        double epsilon = 0.00001;
        double expected = 12*13;
        Assert.assertEquals(expected, actual, epsilon);
    }

    @Test
    public void testMultiplyNegatives() throws Exception {
        double actual = Calculator.multiply(-12, -13);
        double epsilon = 0.00001;
        double expected = (-12)*(-13);
        Assert.assertEquals(expected, actual, epsilon);
    }

    @Test
    public void testMultiplyDifferentSigns() throws Exception {
        double actual = Calculator.multiply(12, -13);
        double epsilon = 0.00001;
        double expected = 12*(-13);
        Assert.assertEquals(expected, actual, epsilon);
    }

    @Test
    public void testDivideEvenly() throws Exception {
        double actual = Calculator.divide(200, -4);
        double epsilon = 0.00001;
        double expected = 200/(-4);
        Assert.assertEquals(expected, actual, epsilon);
    }

    @Test
    public void testDivideWithRemainder() throws Exception {
        double actual = Calculator.divide(207, 8);
        double epsilon = 0.00001;
        double expected = 207.0/8.0;
        Assert.assertEquals(expected, actual, epsilon);
    }

    @Test
    public void testComplexAction() {
        int sum = Calculator.add(112, -5);
        int dif = Calculator.subtract(112, 5);
        double actual = Calculator.divide(sum, dif);
        double epsilon = 0.00001;
        double expected = 1;
        Assert.assertEquals(expected, actual, epsilon);
    }
}