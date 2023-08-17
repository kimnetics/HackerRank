// https://www.hackerrank.com/challenges/luck-balance/problem
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'luckBalance' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. 2D_INTEGER_ARRAY contests
     */

    public static int luckBalance(int k, List<List<Integer>> contests) {
    // Write your code here
        int luckBalance = 0;

        List<Integer> contestLucks = new ArrayList<>();
        for (List<Integer> contest : contests) {
            // Is contest unimportant?
            if (contest.get(1) == 0) {
                // Consider it a loss.
                luckBalance += contest.get(0);
            } else {
                // Keep track of important contests.
                contestLucks.add(contest.get(0));
            }
        }

        // Loop through sorted important contests.
        Collections.sort(contestLucks, Collections.reverseOrder());
        for (int i = 0; i < contestLucks.size(); i++) {
            // Lose as many contests as possible.
            if (i < k) {
                luckBalance += contestLucks.get(i);
            } else {
                luckBalance -= contestLucks.get(i);
            }
        }

        return luckBalance;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> contests = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                contests.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
