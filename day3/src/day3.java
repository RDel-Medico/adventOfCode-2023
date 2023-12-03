import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day3 {

    public static boolean isSymbol(char c) {
        if (c != '.') {
            int k = (int) c;
            if (k >= 48 && k <= 57) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static int getNumber(String[][] map, int i, int j, boolean test) {
        if (i < 0 || j < 0 || i >= map.length || j >= map[i].length) {
            return 0;
        } else {
            int k = (int) map[i][j].charAt(0);
            if (k >= 48 && k <= 57) {
                if (!test && getNumber(map, i, j + 1, test) != 0) {
                    return getNumber(map, i, j + 1, test);
                } else {
                    return getNumber(map, i, j - 1, true) * 10 + (k - 48);
                }
            } else {
                return 0;
            }
        }
    }

    public static boolean isTouched(String[][] map, int i, int j) {
        if (i == 0 && j == 0) { // angle haut gauche
            return isSymbol(map[i][j + 1].charAt(0)) || isSymbol(map[i + 1][j + 1].charAt(0))
                    || isSymbol(map[i + 1][j].charAt(0));
        } else if (i == 0 && j == map[i].length - 1) { // angle haut droite
            return isSymbol(map[i + 1][j].charAt(0)) || isSymbol(map[i + 1][j - 1].charAt(0))
                    || isSymbol(map[i][j - 1].charAt(0));
        } else if (i == map.length - 1 && j == 0) { // angle bas gauche
            return isSymbol(map[i - 1][j].charAt(0)) || isSymbol(map[i - 1][j + 1].charAt(0))
                    || isSymbol(map[i][j + 1].charAt(0));
        } else if (i == map.length - 1 && j == map[i].length - 1) { // angle bas droite
            return isSymbol(map[i - 1][j - 1].charAt(0)) || isSymbol(map[i - 1][j].charAt(0))
                    || isSymbol(map[i][j - 1].charAt(0));
        } else if (i == 0) { // coller en haut
            return isSymbol(map[i][j + 1].charAt(0)) || isSymbol(map[i + 1][j + 1].charAt(0))
                    || isSymbol(map[i + 1][j].charAt(0)) || isSymbol(map[i + 1][j - 1].charAt(0))
                    || isSymbol(map[i][j - 1].charAt(0));
        } else if (i == map.length - 1) { // coller en bas
            return isSymbol(map[i][j - 1].charAt(0)) || isSymbol(map[i - 1][j - 1].charAt(0))
                    || isSymbol(map[i - 1][j].charAt(0)) || isSymbol(map[i - 1][j + 1].charAt(0))
                    || isSymbol(map[i][j + 1].charAt(0));
        } else if (j == 0) { // coller à gauche
            return isSymbol(map[i - 1][j].charAt(0)) || isSymbol(map[i - 1][j + 1].charAt(0))
                    || isSymbol(map[i][j + 1].charAt(0)) || isSymbol(map[i + 1][j + 1].charAt(0))
                    || isSymbol(map[i + 1][j].charAt(0));
        } else if (j == map[i].length - 1) { // coller à droite
            return isSymbol(map[i + 1][j].charAt(0)) || isSymbol(map[i + 1][j - 1].charAt(0))
                    || isSymbol(map[i][j - 1].charAt(0)) || isSymbol(map[i - 1][j - 1].charAt(0))
                    || isSymbol(map[i - 1][j].charAt(0));
        } else { // au centre
            return isSymbol(map[i - 1][j - 1].charAt(0)) || isSymbol(map[i - 1][j].charAt(0))
                    || isSymbol(map[i - 1][j + 1].charAt(0)) || isSymbol(map[i][j + 1].charAt(0))
                    || isSymbol(map[i + 1][j + 1].charAt(0)) || isSymbol(map[i + 1][j].charAt(0))
                    || isSymbol(map[i + 1][j - 1].charAt(0)) || isSymbol(map[i][j - 1].charAt(0));
        }
    }

    public static void main(String[] args) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String[][] map = new String[140][];
            int m = -1;

            while ((line = bufferedReader.readLine()) != null) {
                map[++m] = new String[line.length()];

                for (int i = 0; i < line.length(); i++) {
                    map[m][i] = String.valueOf(line.charAt(i));
                }
            }

            int res = 0;
            for (int i = 0; i < map.length; i++) {
                int number = 0;
                int indexNum = 1;
                int numLen = 0;
                for (int j = map[i].length - 1; j >= 0; j--) {
                    int k = Integer.valueOf(map[i][j].charAt(0));
                    if (k >= 48 && k <= 57) {
                        number = number + (k - 48) * indexNum;
                        indexNum = indexNum * 10;
                        numLen++;
                    } else {
                        boolean added = false;
                        for (int l = 1; l < numLen + 1 && !added; l++) {
                            if (isTouched(map, i, j + l)) {
                                res += number;
                                added = true;
                            }
                        }
                        number = 0;
                        indexNum = 1;
                        numLen = 0;
                    }

                    if (j == 0 && number != 0) {
                        boolean added = false;
                        for (int l = 0; l < numLen && !added; l++) {
                            if (isTouched(map, i, j + l)) {
                                res += number;
                                added = true;
                            }
                        }
                    }
                }
            }

            int res2 = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    int[] adjacent = new int[9];
                    if (map[i][j].charAt(0) == '*') {
                        adjacent[0] = getNumber(map, i - 1, j - 1, false);
                        adjacent[1] = getNumber(map, i - 1, j, false);
                        adjacent[2] = getNumber(map, i - 1, j + 1, false);
                        adjacent[3] = getNumber(map, i, j + 1, false);
                        adjacent[4] = getNumber(map, i + 1, j + 1, false);
                        adjacent[5] = getNumber(map, i + 1, j, false);
                        adjacent[7] = getNumber(map, i + 1, j - 1, false);
                        adjacent[8] = getNumber(map, i, j - 1, false);

                        int num1 = -1;
                        int num2 = -1;
                        boolean tooMany = false;

                        for (int k = 0; k < 9; k++) {
                            System.out.print(adjacent[k] + ", ");
                            if (adjacent[k] != 0) {
                                if (num1 == -1) {
                                    num1 = adjacent[k];
                                } else if (num2 == -1 && adjacent[k] != num1) {
                                    num2 = adjacent[k];
                                } else if (adjacent[k] != num1 && adjacent[k] != num2) {
                                    tooMany = true;
                                }
                            }
                        }
                        System.out.println();
                        if (!tooMany && num2 != -1) {
                            res2 += num1 * num2;
                        }
                    }
                }
            }
            System.out.println("Res : " + res);
            System.out.println("Res2 : " + res2);
        }
    }
}
