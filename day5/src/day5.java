import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day5 {

    public static long convert (long initial, long[][] map) {
        for (int i = 0; i < map.length; i++) {
            long[] m = map[i];
            long start = m[1];
            if (initial >= start && initial < start + m[2]) {
                return m[0] + (initial - start);
            }
        }

        return initial;
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            long[] seeds = null;

            line = bufferedReader.readLine();
            String[] temp = line.split(":");
            temp = temp[1].split(",");

            seeds = new long[temp.length];
            for (int i = 0; i < temp.length; i++) {
                seeds[i] = Long.valueOf(temp[i]);
            }
            
            long[][][] map = new long[7][][];
            String[] mapParser;
            for (int i = 0; i < 7; i++) {
                line = bufferedReader.readLine();
                mapParser = line.split(":");
                mapParser = mapParser[1].split(",");
                map[i] = new long[mapParser.length/3][];
                for (int j = 0; j < mapParser.length/3; j++) {
                    map[i][j] = new long[3];
                    for (int k = 0; k < 3; k++) {
                        map[i][j][k] = Long.valueOf(mapParser[(j*3)+k]);
                    }
                }
            }

            for (int i = 0; i < seeds.length; i+=2) {
                long seedMin = Long.MAX_VALUE;
                long seedMax = seeds[i] + seeds[i+1];
                for (long j = seeds[i]; j < seedMax; j++) {
                    long tempConversion = j;
                    for (int k = 0; k < 7; k++) {
                        tempConversion = convert(tempConversion, map[k]);
                    }
                    if (seedMin > tempConversion) {
                        seedMin = tempConversion;
                    }
                }

                System.out.println("Seed " + i + " finit");
                seeds[i] = seedMin;
                seeds[i+1] = Long.MAX_VALUE;
            }

            long min = Long.MAX_VALUE;
            for (int i = 0; i < seeds.length; i++) {
                if (min > seeds[i]) {
                    min = seeds[i];
                }
            }

            System.out.println("Res : " + min);
        }
    }
}
