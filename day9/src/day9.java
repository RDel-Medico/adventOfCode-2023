import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day9 {

    public static int getPreviousValueOfSequence (int[] sequence) {
        int previousValue = 0;
        int[][] allSequence = getAllDifferenceSequence(sequence);

        for (int i = allSequence.length - 1; i >= 0; i--) {
            previousValue = allSequence[i][0] - previousValue;
        }

        return sequence[0] - previousValue;
    }

    public static int getNextValueOfSequence (int[] sequence) {
        int nextValue = 0;
        int[][] allSequence = getAllDifferenceSequence(sequence);

        for (int i = allSequence.length - 1; i >= 0; i--) {
            nextValue = nextValue + allSequence[i][allSequence[i].length-1];
        }

        return nextValue + sequence[sequence.length-1];
    }

    public static int[][] insertTabInMatrix(int[][] m, int[] t) {
        int[][] temp = new int[m.length + 1][];

        for (int i = 0; i < m.length; i++) {
            temp[i] = new int[m[i].length];
            for (int j = 0; j < m[i].length; j++) {
                temp[i][j] = m[i][j];
            }
        }

        temp[m.length] = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            temp[m.length][i] = t[i];
        }

        return temp;
    }

    public static int[][] getAllDifferenceSequence(int[] sequence) {
        int[][] allSequence = new int[0][];

        int[] newSequence = getDifferenceSequence(sequence);

        allSequence = insertTabInMatrix(allSequence, newSequence);

        while (!onlyZero(newSequence)) {
            newSequence = getDifferenceSequence(newSequence);
            allSequence = insertTabInMatrix(allSequence, newSequence);
        }
        return allSequence;
    }

    public static int[] getDifferenceSequence(int[] sequence) {
        int[] difference = new int[sequence.length - 1];

        for (int i = 0; i < sequence.length - 1; i++) {
            difference[i] = sequence[i + 1] - sequence[i];
        }

        return difference;
    }

    public static boolean onlyZero(int[] sequence) {
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String[][] sequencesS = new String[200][];
            int[][] sequences = new int[200][];
            int l = 0;

            while ((line = bufferedReader.readLine()) != null) {
                sequencesS[l++] = line.split(",");
            }

            for (int i = 0; i < sequencesS.length; i++) {
                sequences[i] = new int[sequencesS[i].length];
                for (int j = 0; j < sequencesS[i].length; j++) {
                    sequences[i][j] = Integer.valueOf(sequencesS[i][j]);
                }
            }
            long res1 = 0;
            long res2 = 0;

            for (int i = 0; i < sequences.length; i++) {
                res1 += getNextValueOfSequence(sequences[i]);
                res2 += getPreviousValueOfSequence(sequences[i]);
            }

            System.out.println("Res1 : " + res1);
            System.out.println("Res2 : " + res2);
        }
    }
}
