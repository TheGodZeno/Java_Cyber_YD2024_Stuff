package MerklesPuzzles;

import java.util.Arrays;

public class PuzzleSolverUtil extends XORUtil {
    public static String[] solvePuzzle(int[][] puzzle) {
        int numbers = puzzle[0].length, N = puzzle.length;
        String[] ans = new String[2];

        // Flatten the 2D array into a list
        int[] vector = Arrays.stream(puzzle)
                .flatMapToInt(Arrays::stream)
                .toArray();

        Arrays.sort(vector);
        int[][] randomNumbers2d = new int[N][numbers];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < numbers; j++)
                randomNumbers2d[i][j] = vector[(i * numbers) + j];

        String serial_number = bitwiseXORArrayOperation(randomNumbers2d[N - 1]);
        String enc_key = "";
        for (int[] row : Arrays.copyOfRange(randomNumbers2d, 0, N - 1))
            enc_key += bitwiseXORArrayOperation(row);

        ans[0] = serial_number;
        ans[1] = enc_key;
        return ans;
    }
}
