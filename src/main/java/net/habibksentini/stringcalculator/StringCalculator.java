package net.habibksentini.stringcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.valueOf;
import static java.util.Arrays.stream;
import static java.util.regex.Pattern.compile;

public class StringCalculator {

    public static String EMPTY = "";
    public static String DEFAULT_DELIMITER = ",";

    public int add(String amountOfNumbers) {
        if (amountOfNumbers.equals(EMPTY)) {
            return 0;
        }
        handleNegativeNumbers(amountOfNumbers);
        String delimiter = DEFAULT_DELIMITER;
        if (hasASpecifiedDelimiter(amountOfNumbers)) {
            delimiter = readSpecifiedDelimiter(amountOfNumbers);
            amountOfNumbers = removeSpecifiedDelimiterLine(amountOfNumbers);
        }
        return add(amountOfNumbers, delimiter);
    }

    private void handleNegativeNumbers(String amountOfNumbers) {
        List<Integer> negativeNumbers = readNegativeNumbers(amountOfNumbers);
        if (negativeNumbers.size() > 0) {
            String exceptionMessage = buildNegativeNumberExceptionMessage(negativeNumbers);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private String buildNegativeNumberExceptionMessage(List<Integer> negativeNumbers) {
        StringBuilder exceptionMessageBuilder = new StringBuilder("negatives not allowed: ");
        negativeNumbers.stream().forEach(negativeNumber -> exceptionMessageBuilder.append(negativeNumber).append(" "));
        String exceptionMessage = exceptionMessageBuilder.toString();
        exceptionMessage = exceptionMessage.substring(0, exceptionMessage.length() - 1);
        return exceptionMessage;
    }

    private List<Integer> readNegativeNumbers(String amountOfNumbers) {
        Pattern pattern = compile("-[0-9]+");
        Matcher matcher = pattern.matcher(amountOfNumbers);
        List<Integer> negativeNumbers = new ArrayList<>();
        while (matcher.find()) {
            negativeNumbers.add(valueOf(matcher.group()));
        }
        return negativeNumbers;
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
        return amountOfNumbers.substring(2, 3);
    }

    private boolean hasASpecifiedDelimiter(String amountOfNumbers) {
        return amountOfNumbers.startsWith("//");
    }

    private String replaceNewlinesWithDelimiters(String amountOfNumbers, String delimiter) {
        return amountOfNumbers.replaceAll("[\\n]", delimiter);
    }

}
