import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day1 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            int res = 0;
            boolean firstDigit = true;
            boolean secondDigit = false;

            Pattern p1 = Pattern.compile("one", Pattern.CASE_INSENSITIVE);
            Pattern p2 = Pattern.compile("two", Pattern.CASE_INSENSITIVE);
            Pattern p3 = Pattern.compile("three", Pattern.CASE_INSENSITIVE);
            Pattern p4 = Pattern.compile("four", Pattern.CASE_INSENSITIVE);
            Pattern p5 = Pattern.compile("five", Pattern.CASE_INSENSITIVE);
            Pattern p6 = Pattern.compile("six", Pattern.CASE_INSENSITIVE);
            Pattern p7 = Pattern.compile("seven", Pattern.CASE_INSENSITIVE);
            Pattern p8 = Pattern.compile("eight", Pattern.CASE_INSENSITIVE);
            Pattern p9 = Pattern.compile("nine", Pattern.CASE_INSENSITIVE);

            while ((line = bufferedReader.readLine()) != null) {
                Matcher m1 = p1.matcher(line);
                Matcher m2 = p2.matcher(line);
                Matcher m3 = p3.matcher(line);
                Matcher m4 = p4.matcher(line);
                Matcher m5 = p5.matcher(line);
                Matcher m6 = p6.matcher(line);
                Matcher m7 = p7.matcher(line);
                Matcher m8 = p8.matcher(line);
                Matcher m9 = p9.matcher(line);

                boolean mF1 = m1.find();
                boolean mF2 = m2.find();
                boolean mF3 = m3.find();
                boolean mF4 = m4.find();
                boolean mF5 = m5.find();
                boolean mF6 = m6.find();
                boolean mF7 = m7.find();
                boolean mF8 = m8.find();
                boolean mF9 = m9.find();

                int[] indexS = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1 };
                int[] indexL = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1 };

                if (mF1) {
                    indexS[0] = m1.start();
                    indexL[0] = indexS[0];
                    while (m1.find()) {
                        indexL[0] = m1.start();
                    }
                }

                if (mF2) {
                    indexS[1] = m2.start();
                    indexL[1] = indexS[1];
                    while (m2.find()) {
                        indexL[1] = m2.start();
                    }
                }

                if (mF3) {
                    indexS[2] = m3.start();
                    indexL[2] = indexS[2];
                    while (m3.find()) {
                        indexL[2] = m3.start();
                    }
                }

                if (mF4) {
                    indexS[3] = m4.start();
                    indexL[3] = indexS[3];
                    while (m4.find()) {
                        indexL[3] = m4.start();
                    }
                }

                if (mF5) {
                    indexS[4] = m5.start();
                    indexL[4] = indexS[4];
                    while (m5.find()) {
                        indexL[4] = m5.start();
                    }
                }

                if (mF6) {
                    indexS[5] = m6.start();
                    indexL[5] = indexS[5];
                    while (m6.find()) {
                        indexL[5] = m6.start();
                    }
                }

                if (mF7) {
                    indexS[6] = m7.start();
                    indexL[6] = indexS[6];
                    while (m7.find()) {
                        indexL[6] = m7.start();
                    }
                }

                if (mF8) {
                    indexS[7] = m8.start();
                    indexL[7] = indexS[7];
                    while (m8.find()) {
                        indexL[7] = m8.start();
                    }
                }

                if (mF9) {
                    indexS[8] = m9.start();
                    indexL[8] = indexS[8];
                    while (m9.find()) {
                        indexL[8] = m9.start();
                    }
                }

                int indexMin = Integer.MAX_VALUE;
                int valueMin = 0;
                for (int i = 0; i < indexS.length; i++) {
                    if (indexS[i] != -1 && indexMin > indexS[i]) {
                        indexMin = indexS[i];
                        valueMin = i + 1;
                    }
                }

                int indexMax = -1;
                int valueMax = 0;
                for (int i = 0; i < indexL.length; i++) {
                    if (indexL[i] != -1 && indexMax < indexL[i]) {
                        indexMax = indexL[i];
                        valueMax = i + 1;
                    }
                }

                int intermediateRes = 0;

                for (int i = 0; i < line.length() && firstDigit; i++) {
                    int k = (int) line.charAt(i);
                    if (k >= 48 && k <= 57) {
                        firstDigit = false;
                        secondDigit = true;
                        if (i > indexMin) {
                            intermediateRes = valueMin * 10;
                        } else {
                            k = k - 48;
                            intermediateRes = k * 10;
                        }
                    }
                }
                for (int i = line.length() - 1; i >= 0 && secondDigit; i--) {
                    int k = (int) line.charAt(i);
                    if (k >= 48 && k <= 57) {
                        secondDigit = false;
                        firstDigit = true;
                        if (i < indexMax) {
                            intermediateRes += valueMax;
                        } else {
                            k = k - 48;
                            intermediateRes += k;
                        }
                    }
                }

                if (intermediateRes == 0) {
                    intermediateRes = valueMin * 10;
                    intermediateRes += valueMax;
                }

                res += intermediateRes;
            }
            System.out.println("Result = " + res + "\n");
        }
    }
}
