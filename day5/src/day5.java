import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day5 {

    public static long convert (long initial, long[][] map) {
        long converted = initial;

        for (int i = 0; i < map.length; i++) {
            if (initial >= map[i][1] && initial < map[i][1] + map[i][2]) {
                converted = map[i][0] + (initial - map[i][1]);
            }
        }

        return converted;
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
                for (int j = 0; j < seeds[i+1]; j++) {
                    long tempConversion = seeds[i]+j;
                    for (int k = 0; k < 7; k++) {
                        tempConversion = convert(tempConversion, map[k]);
                    }
                    if (seedMin > tempConversion) {
                        seedMin = tempConversion;
                    }
                }
                System.out.println("Seed finis");
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
