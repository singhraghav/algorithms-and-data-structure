package com.singraghav.algo.two_pointers;

public class IsPalindrome {

    public static Boolean isPalindrome(String s) {

        int start = 0;
        int end = s.length() - 1;

        boolean answer = true;
        while (start < end) {
            if(s.charAt(start) == s.charAt(end)) {
                start += 1;
                end -= 1;
            } else {
                answer = false;
                break;
            }

        }
        return answer;
    }
}
