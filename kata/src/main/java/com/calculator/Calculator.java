package com.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    final String matchDelimitersPart = "^//(.+)\n";
    final String matchMultipleDelimiter = "\\[(.*?)\\]";    // non-greedy pattern

    private String getDelimiter(String input) {
        // Handle multiple delimiters like //[;][|]
        String delimiterRegex = "[,\n]";

        // Seperates the delimiter part from the numbers start.
        Matcher matcher = Pattern.compile(matchDelimitersPart).matcher(input);

        if (matcher.find()){
            String delimiterPart = matcher.group(1);

            // Matches the multiple delimiters eg -- //[.][*]\n
            Matcher multiDelimMatcher = Pattern.compile(matchMultipleDelimiter).matcher(delimiterPart);
            StringBuilder regexBuilder = new StringBuilder();

            boolean hasMultiple = false;
            while (multiDelimMatcher.find()) {
                if (regexBuilder.length() > 0) regexBuilder.append("|");
                regexBuilder.append(Pattern.quote(multiDelimMatcher.group(1)));
                hasMultiple = true;
            }

            if (hasMultiple) {
                delimiterRegex = regexBuilder.toString();
            } else {
                delimiterRegex = Pattern.quote(delimiterPart);
            }
        }

        return delimiterRegex;
    }


    private String getCleanInput(String rawInput) {
        Matcher matcher = Pattern.compile(matchDelimitersPart).matcher(rawInput.trim());
        System.out.println("Calculator.getCleanInput()");
        if (matcher.find()) {
            return rawInput.substring(matcher.end());
        }
        return rawInput;
    }

    public int add(String rawInput) throws IllegalArgumentException {
        final String delimitersPattern = getDelimiter(rawInput);
        final String cleanedInput = getCleanInput(rawInput);
        
        if (cleanedInput.equals(""))
            return 0;

        int sum=0;
        final String[] nums = cleanedInput.split(delimitersPattern);
        StringBuilder negativesBuilder = new StringBuilder();

        for (String strNum : nums) {
            try {
                final int num = Integer.parseInt(strNum.trim());

                if (num < 0) {
                    negativesBuilder.append(num);
                    negativesBuilder.append(",");
                } else if (num > 1000) {
                    continue;
                }

                sum += num;
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid Input string passed.");
                throw e;
            }

        }
        if (negativesBuilder.length() > 0) {
            negativesBuilder.replace(negativesBuilder.length()-1,negativesBuilder.length(),"");
            throw new IllegalArgumentException("negative numbers not allowed "+negativesBuilder.toString());
        }
        return sum;
    }

}
