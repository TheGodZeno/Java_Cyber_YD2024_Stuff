package MerklesPuzzles;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Eve {
    public Eve() {
    }

    public String[] findKey(int[][][] puzzles, String serial_number){
        int i;
        String[] solution = new String[3];
        String[] curr = new String[2];
        for(i = 0; i < puzzles.length; i++){
            curr = solvePuzzle(puzzles[i]);
            if(curr[0].equals(serial_number)){
                solution[0] = curr[0];
                solution[1] = curr[1];
                solution[2] = Integer.toString(i+1);
                return solution;
            }
        }
        solution[0] = "Not Found";
        solution[1] = "Not Found";
        solution[2] = Integer.toString(i+1);
        return solution;
    }

    public String[] solvePuzzle(int[][] puzzle){
        String[] ans = new String[2];
        int numbers = puzzle[0].length, N = puzzle.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                list.add(puzzle[i][j]);
            }
        }

        int[] vector = new int[list.size()];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = list.get(i);
        }

        Arrays.sort(vector);
        int[][] randomNumbers2d = new int[N][numbers];
        for(int i=0; i<N; i++)
            for(int j=0; j<numbers; j++)
                randomNumbers2d[i][j] = vector[ (i*numbers) + j];
        String serial_number = bitwiseXORArrayOperation(randomNumbers2d[N-1]);
        String enc_key = "";
        for(int[] row: Arrays.copyOfRange(randomNumbers2d, 0, N-1))
            enc_key += bitwiseXORArrayOperation(row);
        ans[0] = serial_number;
        ans[1] = enc_key;
        return ans;
    }

    public static String bitwiseXORArrayOperation(int[] numbers) {
        String res = "";
        for(int number: numbers){
            res += Integer.toString(bitwiseXOROperation(number));
        }
        return res; //Integer.parseInt(res, 2);
    }

    public static int bitwiseXOROperation(int number) {
        int result = 0;
        while (number > 0) {
            result ^= (number & 1);
            number >>= 1;
        }
        return result;
    }
}
