import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day14 {

    public static char[][] rollNorth(char[][] map) {
        char[][] res = map;

        for (int i = 1; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                if (res[i][j] == 'O') {
                    for (int k = i - 1; k >= 0; k--) {
                        if (res[k + 1][j] == 'O' && res[k][j] == '.') {
                            res[k + 1][j] = '.';
                            res[k][j] = 'O';
                        }
                    }
                }
            }
        }
        return map;
    }

    public static boolean samePosition(char[][] m1, char[][] m2) {
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static char[][] cicle(char[][] map) {
        char[][] res = map;

        int width = res.length;
        int height = res[0].length;

        for (int u = 0; u < 220; u++) {
            for (int i = 1; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (res[i][j] == 'O' && res[i - 1][j] == '.') {
                        boolean finished = false;
                        for (int k = i - 1; !finished && k >= 0; k--) {
                            if (res[k + 1][j] == 'O' && res[k][j] == '.') {
                                res[k + 1][j] = '.';
                                res[k][j] = 'O';
                            } else {
                                finished = true;
                            }
                        }
                    }
                }
            }
            for (int i = 1; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (res[j][i] == 'O' && res[j][i - 1] == '.') {
                        boolean finished = false;
                        for (int k = i - 1; !finished && k >= 0; k--) {
                            if (res[j][k + 1] == 'O' && res[j][k] == '.') {
                                res[j][k + 1] = '.';
                                res[j][k] = 'O';
                            } else {
                                finished = true;
                            }
                        }
                    }
                }
            }
            for (int i = width - 2; i >= 0; i--) {
                for (int j = 0; j < height; j++) {
                    if (res[i][j] == 'O' && res[i + 1][j] == '.') {
                        boolean finished = false;
                        for (int k = i + 1; !finished && k < width; k++) {
                            if (res[k - 1][j] == 'O' && res[k][j] == '.') {
                                res[k - 1][j] = '.';
                                res[k][j] = 'O';
                            } else {
                                finished = true;
                            }
                        }
                    }
                }
            }
            for (int i = height - 2; i >= 0; i--) {
                for (int j = 0; j < width; j++) {
                    if (res[j][i] == 'O' && res[j][i + 1] == '.') {
                        boolean finished = false;
                        for (int k = i + 1; !finished && k < height; k++) {
                            if (res[j][k - 1] == 'O' && res[j][k] == '.') {
                                res[j][k - 1] = '.';
                                res[j][k] = 'O';
                            } else {
                                finished = true;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            char[][] maps = new char[100][];
            int l = 0;

            while ((line = bufferedReader.readLine()) != null) {
                maps[l] = new char[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    maps[l][i] = line.charAt(i);
                }
                l++;
            }

            int res = 0;

            maps = cicle(maps);

            for (int i = 0; i < maps.length; i++) {
                for (int j = 0; j < maps.length; j++) {
                    if (maps[i][j] == 'O') {
                        res += maps.length - i;
                    }
                }
            }
            System.out.println(res);
        }
    }
}
