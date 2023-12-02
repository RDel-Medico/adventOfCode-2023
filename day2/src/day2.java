import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day2 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String[] games = new String[100];
            int g = 0;

            while ((line = bufferedReader.readLine()) != null) {
                String[] temp;
                temp = line.split(":");
                games[g++] = temp[1];
            }


            String[] tirages;
            String[] coup;
            String[] temp;
            int amount;
            String couleur;

            int rMin = 0;
            int gMin = 0;
            int bMin = 0;

            int res = 0;
            for (int i = 0; i < 100; i++) {
                tirages = games[i].split(";");
                for (int j = 0; j < tirages.length; j++) {
                    coup = tirages[j].split(",");
                    for (int k = 0; k < coup.length; k++) {
                        temp = coup[k].split("[.]");

                        amount = Integer.parseInt(temp[0]);
                        couleur = temp[1];

                        switch(couleur) {
                            case "r":
                                rMin = amount > rMin ? amount : rMin;
                                break;
                            case "g":
                                gMin = amount > gMin ? amount : gMin;
                                break;
                            case "b":
                                bMin = amount > bMin ? amount : bMin;
                                break;
                            default:
                                break;
                                
                        }
                    }
                }
                res = res + (rMin * bMin * gMin);
                rMin = 0;
                gMin = 0;
                bMin = 0;
            }
            System.out.println("Res : " + res);
        }   
    }
}
