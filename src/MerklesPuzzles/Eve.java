package MerklesPuzzles;

public class Eve extends XORUtil {
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

    public String[] solvePuzzle(int[][] puzzle) {
        return PuzzleSolverUtil.solvePuzzle(puzzle);
    }
}
