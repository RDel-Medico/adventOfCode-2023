import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day10 {

    public static int[] getConnected (String[] field, int x, int y, int prevX, int prevY) {
        switch (field[y].charAt(x)) {
            case '|':
                if (y+1 == prevY) {
                    int [] res = {x, y-1};
                    return res;
                } else {
                    int [] res = {x, y+1};
                    return res;
                }
            case '-':
                if (x+1 == prevX) {
                    int [] res = {x-1, y};
                    return res;
                } else {
                    int [] res = {x+1, y};
                    return res;
                }
            case 'L':
                if (y-1 == prevY) {
                    int [] res = {x+1, y};
                    return res;
                } else {
                    int [] res = {x, y-1};
                    return res;
                }
            case 'J':
                if (y-1 == prevY) {
                    int [] res = {x-1, y};
                    return res;
                } else {
                    int [] res = {x, y-1};
                    return res;
                }
            case '7':
                if (y+1 == prevY) {
                    int [] res = {x-1, y};
                    return res;
                } else {
                    int [] res = {x, y+1};
                    return res;
                }
            case 'F':
                    if (y+1 == prevY) {
                    int [] res = {x+1, y};
                    return res;
                } else {
                    int [] res = {x, y+1};
                    return res;
                }
            default:
                return new int[0];
        }
    }

    public static int[] getConnected(String[] field, int x1, int y1, int prevX1, int prevY1, int x2, int y2, int prevX2, int prevY2) {
        int [] p1 = getConnected(field, x1, y1, prevX1, prevY1);
        int [] p2 = getConnected(field, x2, y2, prevX2, prevY2);

        if (p1[0] == p2[0] && p1[1] == p2[1]) {
            int[] res = {p1[0], p1[1]};
            return res;
        } else if (p1[0] == x2 && p1[1] == y2) {
            return new int[0];
        } else {
            int[] res = {p1[0], p1[1], p2[0], p2[1]};
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String[] field = new String[140];
            int[][] fieldDistance = new int[field.length][];
            int l = 0;

            while ((line = bufferedReader.readLine()) != null) {
                field[l++] = line;
            }

            for (int i = 0; i < field.length; i++) {
                fieldDistance[i] = new int [field[i].length()];
                for (int j = 0; j < field[i].length(); j++) {
                    fieldDistance[i][j] = -1;
                }
            }

            int prevX1 = 0;
            int prevY1 = 0;
            int prevX2 = 0;
            int prevY2 = 0;

            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length(); j++) {
                    if (field[i].charAt(j) == 'S') {
                        prevX1 = j;
                        prevY1 = i;
                        prevX2 = j;
                        prevY2 = i;
                    }
                }
            }

            int x1 = prevX1;
            int y1 = prevY1 - 1;
            int x2 = prevX2 + 1;
            int y2 = prevY2;

            fieldDistance[prevY1][prevX1] = 0;
            fieldDistance[y1][x1] = 1;
            fieldDistance[y2][x2] = 1;

            int[] connected = getConnected(field, x1, y1, prevX1, prevY1, x2, y2, prevX2, prevY2);
            int distance = 1;

            while (connected.length == 4) {
                distance++;
                prevX1 = x1;
                prevY1 = y1;
                prevX2 = x2;
                prevY2 = y2;
                x1 = connected[0];
                y1 = connected[1];
                x2 = connected[2];
                y2 = connected[3];
                fieldDistance[y1][x1] = distance;
                fieldDistance[y2][x2] = distance;
                     
                connected = getConnected (field, x1, y1, prevX1, prevY1, x2, y2, prevX2, prevY2);
            }

            if (connected.length == 2) {
                distance++;
                fieldDistance[connected[1]][connected[0]] = distance;
            }

            for (int i = 0; i < fieldDistance.length; i++) {
                for (int j = 0; j < fieldDistance[i].length; j++) {
                    System.out.print(fieldDistance[i][j]);
                }
                System.out.println();
            }

            System.out.println(distance);
        }
    }
}
