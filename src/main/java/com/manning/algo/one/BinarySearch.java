package com.manning.algo.one;

import java.util.Arrays;

public class BinarySearch {

    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = {4, 9, 9, 5, 6, 9};
        Arrays.sort(whitelist);

        int key = 5;
        if (rank(key, whitelist) == -1) {
            System.out.println(key);
        }
    }
}
