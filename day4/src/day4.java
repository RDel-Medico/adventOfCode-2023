import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day4 {

    public static boolean inTab(int[] arr, int a) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == a) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String[][] sets = new String[212][];

            int l = 0;
            while ((line = bufferedReader.readLine()) != null) {
                sets[l++] = line.split("[|]");
            }

            String[][] winningSet = new String[212][];
            String[][] ourSet = new String[212][];

            for (int i = 0; i < sets.length; i++) {
                winningSet[i] = sets[i][1].split(",");
                ourSet[i] = sets[i][2].split(",");
            }

            int[][] intWinningSet = new int[winningSet.length][];
            int[][] intOurSet = new int[ourSet.length][];
            for (int i = 0; i < winningSet.length; i++) {
                intWinningSet[i] = new int[winningSet[i].length];
                for (int j = 0; j < winningSet[i].length; j++) {
                    intWinningSet[i][j] = Integer.valueOf(winningSet[i][j]);
                }
            }

            for (int i = 0; i < ourSet.length; i++) {
                intOurSet[i] = new int[ourSet[i].length];
                for (int j = 0; j < ourSet[i].length; j++) {
                    intOurSet[i][j] = Integer.valueOf(ourSet[i][j]);
                }
            }

            int res = 0;

            for (int i = 0; i < intOurSet.length; i++) {
                int value = 0;
                for (int j = 0; j < intOurSet[i].length; j++) {
                    if (inTab(intWinningSet[i], intOurSet[i][j])) {
                        value = value == 0 ? 1 : value * 2;
                    }
                }
                res += value;
            }

            int[] copies = new int[212];
            for (int i = 0; i < copies.length; i++) {
                copies[i] = 0;
            }

            for (int i = 0; i < intOurSet.length; i++) {
                int nbMatching = 0;
                for (int j = 0; j < intOurSet[i].length; j++) {
                    if (inTab(intWinningSet[i], intOurSet[i][j])) {
                        nbMatching++;
                    }
                }
                for (int k = i + 1; k < 212 && k < i + 1 + nbMatching; k++) {
                    copies[k] = copies[k] + copies[i] + 1;
                }
            }

            int res2 = 0;
            for (int i = 0; i < copies.length; i++) {
                res2 += (copies[i] + 1);
            }

            System.out.println(res);
            System.out.println(res2);
        }
    }
}
