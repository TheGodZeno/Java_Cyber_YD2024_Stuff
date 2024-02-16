package MerklesPuzzles;

import java.util.*;

public class Bob {

    public Bob() {
    }

    public String[]  solvePuzzle(int[][] puzzle){
        String[] ans = new String[2];
        int numbers = puzzle[0].length, N = puzzle.length;
        List<Integer> list = new ArrayList<Integer>();
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

    public String[] choosePuzzle(int[][][] puzzles){
        Random random = new Random();
        int puzzle_number = random.nextInt(0, puzzles.length-1);
        System.out.println("Bob chose puzzle number " + (puzzle_number+1) + ".\nThis is the puzzle: " + Arrays.deepToString(puzzles[puzzle_number]));
        return solvePuzzle(puzzles[puzzle_number]);
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
