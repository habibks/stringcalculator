package net.habibksentini.stringcalculator;

import static java.util.Arrays.stream;

public class StringCalculator {

    public static String EMPTY = "";
    public static String DELIMITER = ",";

    public int add(String amountOfNumbers) {
        if (amountOfNumbers.equals(EMPTY)) {
            return 0;
        }
        amountOfNumbers = replaceNewlineWithDelimiter(amountOfNumbers);
        String[] numbers = amountOfNumbers.split(DELIMITER);
        return stream(numbers)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private String replaceNewlineWithDelimiter(String amountOfNumbers) {
        return amountOfNumbers.replaceAll("[\\n]", DELIMITER);
    }

}
