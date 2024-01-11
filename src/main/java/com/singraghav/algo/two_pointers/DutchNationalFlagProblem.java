package com.singraghav.algo.two_pointers;

public class DutchNationalFlagProblem {

    public static int[] sortColors (int[] colors) {

        // Replace this placeholder return statement with your code

        int low = 0;
        int mid = 0;
        int high = colors.length - 1;

        while (mid < high) {
            if (colors[mid] == 0) {
                swap(colors, low, mid);
                low += 1;
                mid += 1;
            } else if (colors[mid] == 1) {
                mid += 1;
            } else {
                swap(colors, mid, high);
                high -= 1;
            }
        }
        return colors;
    }

    public static void swap(int[] colors, int i, int j) {
        int temp = colors[i];
        colors[i] = colors[j];
        colors[j] = temp;
    }
}
