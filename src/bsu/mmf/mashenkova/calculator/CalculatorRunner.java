package bsu.mmf.mashenkova.calculator;

import java.util.Scanner;

/**
 * Class to run calculator
 */
public class CalculatorRunner {
    public final static String CONTINUE_MESSAGE = "If you want to continue, type 1, else type anything you want";
    public final static String FIRST_NUMBER_MESSAGE = "Enter the first number";
    public final static String OPERATION_MESSAGE = "Enter the operation sign: +, -, * or /";
    public final static String SECOND_NUMBER_MESSAGE = "Enter the second number";
    public final static String RESULT_MESSAGE = "Result ";
    public final static String NOT_SUPPORTED_MESSAGE = "Not supported operation.";

    public static void main(String[] args) {
        boolean wantToCalculate = true;
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            while(wantToCalculate) {
                runCalculatorOperation(scanner);
                System.out.println(CONTINUE_MESSAGE);
                if(scanner.hasNext()) {
                    wantToCalculate = scanner.hasNextInt() && scanner.nextInt() == 1;
                }
            }
        } finally {
            if(scanner!=null) {
                scanner.close();
            }
        }
    }

    private static void runCalculatorOperation(Scanner scanner) {
        int x = 0;
        int y = 0;
        String operation = "";
        System.out.println();
        System.out.println(FIRST_NUMBER_MESSAGE);
        if(scanner.hasNext()) {
            while(!scanner.hasNextInt()) {
                System.out.println(FIRST_NUMBER_MESSAGE);
                scanner.nextLine();
            }
            x = scanner.nextInt();
        }
        System.out.println(OPERATION_MESSAGE);
        if(scanner.hasNext()) {
            operation = scanner.nextLine();
            while(!operation.matches("[+|\\-|*|/]")) {
                System.out.println(OPERATION_MESSAGE);
                operation = scanner.nextLine();
            }
        }
        System.out.println(SECOND_NUMBER_MESSAGE);
        if(scanner.hasNext()) {
            while(!scanner.hasNextInt()) {
                System.out.println(SECOND_NUMBER_MESSAGE);
                scanner.nextLine();
            }
            y = scanner.nextInt();
        }
        switch (operation) {
            case "+":
                System.out.println(RESULT_MESSAGE + Calculator.add(x, y));
                break;
            case "-":
                System.out.println(RESULT_MESSAGE + Calculator.subtract(x, y));
                break;
            case "*":
                System.out.println(RESULT_MESSAGE + Calculator.multiply(x, y));
                break;
            case "/":
                System.out.println(RESULT_MESSAGE + Calculator.divide(x, y));
                break;
            default:
                System.out.println(NOT_SUPPORTED_MESSAGE);
                break;
        }
    }
}
