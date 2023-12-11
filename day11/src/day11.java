import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;



public class day11 {

    public static class Point {
        int x;
        int y;
    
        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    
    
        public int distance (Point p) {
            return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
        }
    }

    public static final int VOID = 0;
    public static final int HORIZONTALLY_EXPANDED_VOID = -1;
    public static final int VERTICALLY_EXPANDED_VOID = -2;
    public static final int EXPANDED_VOID = -3;

    public static Point [] addPlanet (Point [] p, Point x) {
        Point [] res = new Point [p.length + 1];
        for (int i = 0; i < p.length; i++) {
            res[i] = p[i];
        }
        res[p.length] = x;

        return res;
    }

    public static int nbVertical (int x1, int x2, int[][] u) {
        int res = 0;

        for (int i = Math.min(x1, x2); i < Math.max(x1, x2); i++) {
            if (u[0][i] == VERTICALLY_EXPANDED_VOID || u[0][i] == EXPANDED_VOID) {
                res++;
            }
        }

        return res;
    }

    public static int nbHorizontal (int y1, int y2, int[][] u) {
        int res = 0;

        for (int i = Math.min(y1, y2); i < Math.max(y1, y2); i++) {
            if (u[i][0] == HORIZONTALLY_EXPANDED_VOID || u[i][0] == EXPANDED_VOID) {
                res++;
            }
        }

        return res;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            int[][] universe = new int[140][];
            int planet = 1;
            int l = 0;

            Point[] planets = new Point [0];

            while ((line = bufferedReader.readLine()) != null) {
                
                universe[l] = new int[line.length()];

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '.') {
                        universe[l][i] = VOID;
                    } else {
                        universe[l][i] = planet++;
                        Point temp = new Point(i, l);
                        planets = addPlanet(planets, temp);
                    }
                }
                l++;
            }

            boolean lineEmpty = true;
            for (int i = 0; i < universe.length; i++) {
                lineEmpty = true;
                for (int j = 0; j < universe[i].length; j++) {
                    if (universe[i][j] != VOID) {
                        lineEmpty = false;
                    }
                }
                if (lineEmpty) {
                    for (int j = 0; j < universe[i].length; j++) {
                        universe[i][j] = HORIZONTALLY_EXPANDED_VOID;
                    }
                }
            }

            for (int i = 0; i < universe[0].length; i++) {
                lineEmpty = true;
                for (int j = 0; j < universe.length; j++) {
                    if (!(universe[j][i] == VOID || universe[j][i] == HORIZONTALLY_EXPANDED_VOID)) {
                        lineEmpty = false;
                    }
                }
                if (lineEmpty) {
                    for (int j = 0; j < universe.length; j++) {
                        if (universe[j][i] == HORIZONTALLY_EXPANDED_VOID) {
                            universe[j][i] = EXPANDED_VOID;
                        } else {
                            universe[j][i] = VERTICALLY_EXPANDED_VOID;
                        }
                    }
                }
                
            }

            BigInteger res = new BigInteger("0");
            BigInteger val;
            for (int i = 0; i < planets.length - 1; i++) {
                for (int j = i + 1; j < planets.length; j++) {
                    val = new BigInteger (String.valueOf(planets[i].distance(planets[j])));
                    res = res.add(val);
                    val = new BigInteger(String.valueOf(nbVertical(planets[i].x, planets[j].x, universe)));
                    val = val.multiply(new BigInteger("999999"));
                    res = res.add(val);
                    val = new BigInteger(String.valueOf(nbHorizontal(planets[i].y, planets[j].y, universe)));
                    val = val.multiply(new BigInteger("999999"));//999999
                    res = res.add(val);
                }
            }


            System.out.println(res.toString());
        }
    }
}
