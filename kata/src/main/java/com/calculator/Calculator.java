package com.calculator;

public class Calculator {

    public int add(String rawInput) throws IllegalArgumentException {
        final String input = rawInput.trim();
        if (input.equals(""))
            return 0;
        
        int sum=0;
        final String[] nums = input.split("[\\n,]");
        for (int i = 0; i < nums.length; i++) {

            System.out.println(nums[i]);
            final int num = Integer.parseInt(nums[i].trim());

            if (num < 0)
                throw new IllegalArgumentException("negative numbers not allowed "+num);

            sum += num;
        }

        return sum;
    }

}
