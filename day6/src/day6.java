import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class day6 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            int nbSolution = 0;
            int time = 47707566;
            BigInteger timeTobeat = new BigInteger("282107911471062");

            for (int j = 0; j < time; j++) {
                if (-1 == timeTobeat.divide(new BigInteger(Integer.toString(time-j))).compareTo(new BigInteger(Integer.toString(j)))) {
                    nbSolution++;
                }
            }
            System.out.println("res : " + nbSolution);
        }
    }
}
