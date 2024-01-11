package com.singraghav.algo.fast_slow_pointer;

public class HappyNumber {

    public static boolean isHappyNumber (int n) {

        // Replace this placeholder return statement with your code
        int slow = n;
        int fast = sumOfSquares(n);

        boolean result = true;

        while (true) {
            if (fast == 1) break;
            else {
                if (fast == slow) {
                    result = false;
                    break;
                }
                slow = sumOfSquares(slow);
                fast = sumOfSquares(sumOfSquares(fast));
            }
        }

        return result;
    }

    public static  int sumOfSquares(int n) {
        int sum = 0;

        while (n > 0) {
            int remainder = n % 10;
            sum += (remainder * remainder);
            n = n / 10;
        }

        return sum;
    }
}
