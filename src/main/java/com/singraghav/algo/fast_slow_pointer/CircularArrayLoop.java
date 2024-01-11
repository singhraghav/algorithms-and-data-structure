package com.singraghav.algo.fast_slow_pointer;

public class CircularArrayLoop {

    public static boolean circularArrayLoop(int[] nums) {
        int size = nums.length;

        for (int i = 0; i < size; i++) {
            int slow = i, fast = i;
            boolean forward = nums[i] > 0;

            while (true) {
                slow = nextStep(slow, nums[slow], size);

                if (isNotCycle(nums, forward, slow))
                    break;

                fast = nextStep(fast, nums[fast], size);

                if (isNotCycle(nums, forward, fast))
                    break;

                fast = nextStep(fast, nums[fast], size);

                if (isNotCycle(nums, forward, fast))
                    break;

                if (slow == fast)
                    return true;
            }
        }

        return false;
    }

    // A function to calculate the next step
    public static int nextStep(int pointer, int value, int size) {
        int result = (pointer + value) % size;
        if (result < 0)
            result += size;
        return result;
    }

    // A function to detect a cycle doesn't exist
    public static boolean isNotCycle(int[] nums, boolean prevDirection, int pointer) {
        boolean currDirection = nums[pointer] >= 0;

        if (prevDirection != currDirection || Math.abs(nums[pointer] % nums.length) == 0) {
            return true;
        }

        return false;
    }


}
