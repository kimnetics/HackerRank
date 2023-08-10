// https://www.hackerrank.com/challenges/java-bitset/problem
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String config = scanner.nextLine();
        String[] configElements = config.split(" ");
        int n = Integer.parseInt(configElements[0]);
        int m = Integer.parseInt(configElements[1]);

        BitSet[] bitset = new BitSet[3];
        // bitset[0] not used.
        bitset[1] = new BitSet(n);
        bitset[2] = new BitSet(n);

        for (int i = 0; i < m; i++) {
            String command = scanner.nextLine();
            String[] commandElements = command.split(" ");
            String operation = commandElements[0];
            int operand1 = Integer.parseInt(commandElements[1]);
            int operand2 = Integer.parseInt(commandElements[2]);

            switch (operation) {
                case "AND":
                    bitset[operand1].and(bitset[operand2]);
                    break;
                case "OR":
                    bitset[operand1].or(bitset[operand2]);
                    break;
                case "XOR":
                    bitset[operand1].xor(bitset[operand2]);
                    break;
                case "FLIP":
                    bitset[operand1].flip(operand2);
                    break;
                case "SET":
                    bitset[operand1].set(operand2);
                    break;
                default:
                    System.out.printf("Unexpected operation '%s' encountered.", operation);
                    System.exit(1);
            }

            System.out.printf("%d %d\n", bitset[1].cardinality(), bitset[2].cardinality());
        }

        scanner.close();
    }
}
