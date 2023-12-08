import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day8 {

    public static int getNodeIndex (String node, String[][] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            if (node.equals(nodes[i][0])) {
                return i;
            }
        }
        return -1;
    }

    public static boolean allEndZ (String[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            if (!nodes[i].endsWith("Z")) {
                return false;
            }
        }
        return true;
    }

    public static long ppcm (long a, long b) {
        long ppcm = 0;
        long biggest;

        if (a > b) {
            ppcm = a;
            biggest = a;
        } else {
            ppcm = b;
            biggest = b;
        }

        while(true) {
            if (ppcm%a == 0 && ppcm%b == 0) {
                return ppcm;
            } else {
                ppcm += biggest;
            }
        }
    }

    public static long ppcm (int [] numbers) {
        long res = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            res = ppcm(res, numbers[i]);
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String directions = null;
            String[][] nodes = new String[754][];
            int l = 0;

            directions = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                nodes[l++] = line.split(",");
            }

            String [] currentStates = new String[0];

            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i][0].endsWith("A")) {
                    String[] temp = new String[currentStates.length+1];
                    for (int j = 0; j < currentStates.length; j++) {
                        temp[j] = currentStates[j];
                    }
                    temp[currentStates.length] = nodes[i][0];
                    currentStates = temp;
                }
            }

            int currentMovement = 0;
            int res = 0;

            int [] indexNodes = new int [currentStates.length];

            int [] loopTime = new int [currentStates.length];

            for (int i = 0; i < loopTime.length; i++) {
                loopTime[i] = 0;
            }

            boolean allLoopFound = false;
            while (!allEndZ(currentStates) && !allLoopFound) {
                for (int i = 0; i < currentStates.length; i++) {
                    indexNodes[i] = getNodeIndex(currentStates[i], nodes);
                }

                if (directions.charAt(currentMovement) == 'L') {
                    for (int i = 0; i < currentStates.length; i++) {
                        currentStates[i] = nodes[indexNodes[i]][1];
                    }
                } else {
                    for (int i = 0; i < currentStates.length; i++) {
                        currentStates[i] = nodes[indexNodes[i]][2];
                    }
                }

                allLoopFound = true;
                for (int i = 0; i < loopTime.length; i++) {
                    if (currentStates[i].endsWith("Z") && loopTime[i] == 0) {
                        loopTime[i] = res + 1;
                    }
                    if (loopTime[i] == 0) {
                        allLoopFound = false;
                    }
                }
                currentMovement = (currentMovement + 1) % directions.length();

                res++;
            }

            System.out.println("Res : " + ppcm(loopTime));
        }
    }
}
