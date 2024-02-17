package MerklesPuzzles;

import java.util.*;

public class Bob extends XORUtil {

    public Bob() {
    }

    public String[] solvePuzzle(int[][] puzzle) {
        return PuzzleSolverUtil.solvePuzzle(puzzle);
    }

    public String[] choosePuzzle(int[][][] puzzles) {
        Random random = new Random();
        int puzzle_number = random.nextInt(puzzles.length);
        System.out.println("Bob chose puzzle number " + (puzzle_number + 1) + ".\nThis is the puzzle: " + Arrays.deepToString(puzzles[puzzle_number]));
        return PuzzleSolverUtil.solvePuzzle(puzzles[puzzle_number]);
    }


}
