import MerklesPuzzles.Alice;
import MerklesPuzzles.Bob;
import MerklesPuzzles.Eve;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Alice A = new Alice();
        Bob B = new Bob();
        Eve E = new Eve();
        A.createPuzzles(8,256);
        System.out.flush();
        System.out.println(Arrays.deepToString(A._puzzles));
        for (int j = 0; j < A._puzzles.length; j++) {
            System.out.println("Puzzle-Number " + (j + 1) + ". " + Arrays.deepToString(A._puzzles[j]));
            String[] curr_arr_sol = B.solvePuzzle(A._puzzles[j]);
            System.out.println("The serial number is: " + curr_arr_sol[0] + ".\nThe key is: " + curr_arr_sol[1]);
        }
        String[] res = B.choosePuzzle(A._puzzles);
        System.out.println("The serial number is: " + res[0] + ".\nThe key is: " + res[1]);
        System.out.println(Arrays.deepToString(A._puzzles));
        String[] eve_res = E.findKey(A._puzzles, res[0]);
        System.out.println("Eve found that for serial_number: " + res[0] + ".\nThe corresponding key is: " + eve_res[1]);
//        System.out.println(Arrays.deepToString(A._puzzles));
//        A.findKey(255);
//        A.findKey(1275);
    }
    /*
    The system chose puzzle number 8.
    This is the puzzle: [[3974, 1415, 2357, 2517, 3205, 3238, 963, 3746], [935, 3903, 232, 3181, 1029, 1511, 3938, 3099], [3777, 3320, 3607, 539, 3220, 1220, 2491, 1302], [2523, 2524, 2313, 1591, 3212, 133, 972, 1466], [4004, 66, 3198, 1137, 888, 2748, 1279, 439], [2925, 1948, 3346, 3791, 36, 911, 2013, 3415], [3869, 2606, 1310, 3850, 837, 2411, 3727, 4051], [1704, 2683, 869, 2055, 3813, 2886, 3678, 4090]]
    The serial number is: 00011110.
    The key is: 00101110011001101100101111000101010010001011101101000010
     */

}