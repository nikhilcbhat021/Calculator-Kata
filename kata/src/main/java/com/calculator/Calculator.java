package com.calculator;

public class Calculator {

    public int add(String rawInput) {
        final String input = rawInput.trim();
        if (input.equals(""))
            return 0;
        
        int sum=0;
        final String[] nums = input.split(",");
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
            final int num = Integer.parseInt(nums[i].trim());
            sum += num;
        }

        return sum;
    }

}
