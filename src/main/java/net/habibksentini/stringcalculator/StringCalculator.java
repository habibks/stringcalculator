package net.habibksentini.stringcalculator;

import static java.lang.String.valueOf;
import static java.util.Arrays.stream;

public class StringCalculator {

    public static String EMPTY = "";
    public static String DELIMITER = ",";

    public int add(String amountOfNumbers) {
        if (amountOfNumbers.equals(EMPTY)) {
            return 0;
        }

        String delimiter = DELIMITER;
        if(amountOfNumbers.startsWith("//")){
            delimiter = amountOfNumbers.substring(2,3);
            amountOfNumbers = amountOfNumbers.substring(4);
        };
        amountOfNumbers = replaceNewlineWithDelimiter(amountOfNumbers, delimiter);
        String[] numbers = amountOfNumbers.split(delimiter);
        return stream(numbers)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private String replaceNewlineWithDelimiter(String amountOfNumbers, String delimiter) {
        return amountOfNumbers.replaceAll("[\\n]", delimiter);
    }

}
