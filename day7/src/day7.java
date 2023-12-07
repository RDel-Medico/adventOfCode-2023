import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day7 {

    static final int INPUT_SIZE = 1000;
    static final int HAND_SIZE = 5;

    public static int cardValue (char card) {
        switch (card) {
            case 'A':
                return 14;
            case 'K':
                return 13;
            case 'Q':
                return 12;
            case 'T':
                return 11;
            case '9':
                return 10;
            case '8':
                return 9;
            case '7':
                return 8;
            case '6':
                return 7;
            case '5':
                return 6;
            case '4':
                return 5;
            case '3':
                return 4;
            case '2':
                return 3;
            case 'J':
                return -1;
            default:
                return 0;
        }
    }

    public static int handValue (String hand) {
        int[] val = new int[13];
        for (int i = 0; i < val.length; i++) {
            val[i] = 0;
        }

        int nbJ = 0;
        for (int i = 0; i < HAND_SIZE; i++) {
            if (cardValue(hand.charAt(i)) == -1) {
                nbJ++;
            } else {
                val[cardValue(hand.charAt(i))-2]++;
            }
        }

        int indexMax = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < val.length; i++) {
            if (val[i] > max) {
                indexMax = i;
                max = val[i];
            }
        }

        val[indexMax]+=nbJ;

        int nb2 = 0;
        int nb3 = 0;
        int nb4 = 0;
        int nb5 = 0;

        for (int i = 0; i < val.length; i++) {
            switch (val[i]) {
                case 2:
                    nb2++;
                    break;
                case 3:
                    nb3++;
                    break;
                case 4:
                    nb4++;
                    break;
                case 5:
                    nb5++;
                    break;
                default:
                    break;
            }
        }

        if (nb5 > 0) {
            return 20;
        } else if (nb4 > 0) {
            return 19;
        } else if (nb3 >= 1 && nb2 >= 1) {
            return 18;
        } else if (nb3 >= 1) {
            return 17;
        } else if (nb2 >= 2) {
            return 16;
        } else if (nb2 >= 1) {
            return 15;
        } else {
            return 0;
        }
    }

    public static int compareValue (char card1, char card2) {
        int card1Value = cardValue(card1);
        int card2Value = cardValue(card2);

        if (card1Value > card2Value) {
            return 1;
        } else if (card2Value > card1Value) {
            return -1;
        } else {
            return 0;
        }
    }

    public static int compareHand (String hand1, String hand2) {
        int valueHand1 = handValue(hand1);
        int valueHand2 = handValue(hand2);

        if (valueHand1 > valueHand2) {
            return 1;
        } else if (valueHand2 > valueHand1) {
            return -1;
        } else {
            for (int i = 0; i < HAND_SIZE; i++) {
                int compareValue = compareValue(hand1.charAt(i), hand2.charAt(i));
                if (compareValue != 0) {
                    return compareValue;
                }
            }
        }
        return 0;
    }

    public static String[] insertHand (String[] hands, String hand, int[] bids, int bid) {
        String[] inserted = hands;
        boolean done = false;
        for (int i = 0; i < hands.length && !done; i++) {
            
            if (hands[i] == "empty") {
                inserted[i] = hand;
                bids[i] = bid;
                done = true;
            } else if (compareHand(hands[i], hand) != -1) {
                done = true;
                String temp1 = hands[i];
                String temp2;
                int btemp1 = bids[i];
                int btemp2;
                inserted[i] = hand;
                bids[i] = bid;
                for (int j = i+1; j < hands.length; j++) {
                    temp2 = hands[j];
                    inserted[j] = temp1;
                    temp1 = temp2;

                    btemp2 = bids[j];
                    bids[j] = btemp1;
                    btemp1 = btemp2;
                }
            }
        }
        return inserted;
    }

    public static void main(String[] args) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String[] hands = new String [INPUT_SIZE];
            int[] bids = new int [INPUT_SIZE];
            int l = 0;

            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split(",");
                hands[l] = temp[0];
                bids[l++] = Integer.valueOf(temp[1]);
            }

            String[] orderedHands = new String [hands.length];
            int[] orderedBids = new int [bids.length];

            for (int i = 0; i < orderedHands.length; i++) {
                orderedHands[i] = "empty";
            }
            
            for (int i = 0; i < hands.length; i++) {
                orderedHands = insertHand(orderedHands, hands[i], orderedBids, bids[i]);
            }

            int res = 0;
            for (int i = 0; i < orderedHands.length; i++) {
                res = res + (orderedBids[i] * (i+1));
            }

            System.out.println("Res : " + res);
        }
    }
}
