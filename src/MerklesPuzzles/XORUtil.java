package MerklesPuzzles;

public class XORUtil {
    public static String bitwiseXORArrayOperation(int[] numbers) {
        String res = "";
        for (int number : numbers) {
            res += Integer.toString(bitwiseXOROperation(number));
        }
        return res;
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
