package MerklesPuzzles;

import Trees.AvlTree;
import Trees.BinarySearchTree;

import java.util.*;

public class Alice extends XORUtil{
    public int[][][] _puzzles;
    BinarySearchTree answers = new AvlTree();

    public Alice() {
    }

    public void createPuzzles(int N, int K) {
        int numbers = 8;
        int puzzle = 0;
        int P = (int)Math.pow(N,4);
        int roof = (int)Math.pow(2, numbers);
        K = K > roof ? roof : K;
        _puzzles = new int[K][N][numbers];
        Random random = new Random();

        while(puzzle < K) {
            int[] randomNumbers = new int[numbers*N];
            int[] copy_randomNumbers = new int[numbers*N];
            for (int i = 0; i < numbers*N; i++)
                    randomNumbers[i] = copy_randomNumbers[i] = random.nextInt(P);
            Arrays.sort(randomNumbers);
            int[][] randomNumbers2d = new int[N][numbers];
            for(int i=0; i<N; i++)
                for(int j=0; j<numbers; j++)
                    randomNumbers2d[i][j] = randomNumbers[ (i*numbers) + j];
            int serial_number = Integer.parseInt(bitwiseXORArrayOperation(randomNumbers2d[N-1]), 2);
            if(answers.searchNode(serial_number) != null) continue;
            else{
                String enc_key = "";
                for(int[] row: Arrays.copyOfRange(randomNumbers2d, 0, N-1))
                    enc_key += bitwiseXORArrayOperation(row);
                answers.insertNode(serial_number, enc_key);
            }
            for(int i=0; i<N; i++)
                for(int j=0; j<numbers; j++)
                    randomNumbers2d[i][j] = copy_randomNumbers[ (i*numbers) + j];
            _puzzles[puzzle] = randomNumbers2d;
            puzzle++;
        }
    }

    public int[][][] getPuzzles() {
        int K = _puzzles.length;
        int[][][] copy = new int[K][][];

        for (int k = 0; k < K; k++) {
            int N = _puzzles[k].length;
            copy[k] = new int[N][];

            for (int n = 0; n < N; n++) {
                int numElements = _puzzles[k][n].length;
                copy[k][n] = new int[numElements];

                // Copy
                for (int element = 0; element < numElements; element++) {
                    copy[k][n][element] = _puzzles[k][n][element];
                }
            }
        }

        return copy;
    }

    public String[] findKey(int s_num){
        return answers.searchNode(s_num);
    }

}