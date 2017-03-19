package net.habibksentini.stringcalculator;

import static java.lang.String.valueOf;
import static java.util.Arrays.stream;

public class StringCalculator {

    public static String EMPTY = "";
    public static String DEFAULT_DELIMITER = ",";

    public int add(String amountOfNumbers) {
        if (amountOfNumbers.equals(EMPTY)) {
            return 0;
        }
        String delimiter = DEFAULT_DELIMITER;
        if(hasASpecifiedDelimiter(amountOfNumbers)){
            delimiter = readSpecifiedDelimiter(amountOfNumbers);
            amountOfNumbers = removeSpecifiedDelimiterLine(amountOfNumbers);
        };
        return add(amountOfNumbers, delimiter);
    }



    private int add(String amountOfNumbers, String delimiter) {
        amountOfNumbers = replaceNewlinesWithDelimiters(amountOfNumbers, delimiter);
        String[] numbers = amountOfNumbers.split(delimiter);
        return stream(numbers)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private String removeSpecifiedDelimiterLine(String amountOfNumbers) {
        return amountOfNumbers.substring(4);
    }

    private String readSpecifiedDelimiter(String amountOfNumbers) {
        return amountOfNumbers.substring(2,3);
    }

    private boolean hasASpecifiedDelimiter(String amountOfNumbers) {
        return amountOfNumbers.startsWith("//");
    }

    private String replaceNewlinesWithDelimiters(String amountOfNumbers, String delimiter) {
        return amountOfNumbers.replaceAll("[\\n]", delimiter);
    }

}
