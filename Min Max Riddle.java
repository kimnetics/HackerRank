// https://www.hackerrank.com/challenges/min-max-riddle/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the riddle function below.
    static long[] riddle(long[] arr) {
        // complete this function
        // Concept from:
        // https://www.geeksforgeeks.org/find-the-maximum-of-minimums-for-every-window-size-in-a-given-array/
        int arrLength = arr.length;

        Deque<Integer> stack = new ArrayDeque<>();

        int[] left = new int[arrLength + 1];
        Arrays.fill(left, -1);

        for (int i = 0; i < arrLength; i++) {
            // Pop values until current array value is greater than stack value.
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            // Is there a stack value smaller than current array value?
            if (!stack.isEmpty()) {
                // Save index of smaller stack value
                left[i] = stack.peek();
            }

            // Add index of current array value to stack.
            stack.push(i);
        }

        stack = new ArrayDeque<>();

        int[] right = new int[arrLength + 1];
        Arrays.fill(right, arrLength);

        for (int i = arrLength - 1; i >= 0; i--) {
            // Pop values until current array value is greater than stack value.
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            // Is there a stack value smaller than current array value?
            if (!stack.isEmpty()) {
                right[i] = stack.peek();
            }

            // Add index of current array value to stack.
            stack.push(i);
        }

        long[] answer = new long[arrLength + 1];

        // Find maximum minimum values for all window sizes.
        for (int i = 0; i < arrLength; i++) {
            int windowSize = right[i] - left[i] - 1;
            answer[windowSize] = Math.max(answer[windowSize], arr[i]);
        }

        // For windows sizes not yet filled in, use value to right.
        for (int i = arrLength - 1; i >= 1; i--) {
            answer[i] = Math.max(answer[i], answer[i + 1]);
        }

        return Arrays.copyOfRange(answer, 1, arrLength + 1);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] arr = new long[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr[i] = arrItem;
        }

        long[] res = riddle(arr);

        for (int i = 0; i < res.length; i++) {
            bufferedWriter.write(String.valueOf(res[i]));

            if (i != res.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
