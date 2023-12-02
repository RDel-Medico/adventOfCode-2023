import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day14 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String[] elements = null;

            while ((line = bufferedReader.readLine()) != null) {
                elements = line.split(".");
            }

            for (int i = 0; i < elements.length; i++) {
                System.out.println(elements[i] + "\n");
            }
        }
    }
}
