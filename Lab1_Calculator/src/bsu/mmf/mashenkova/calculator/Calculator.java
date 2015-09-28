package bsu.mmf.mashenkova.calculator;

/**
 * Calculator class provides operations on integer numbers like addition, subtraction, multiplication and division
 */
public class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static double multiply(int a, int b) {
        return a*b;
    }

    public static double divide(int a, int b) {
        return (double)a/b;
    }
}
