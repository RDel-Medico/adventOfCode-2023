import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day15 {

    public static int hashed (int hash, char x) {
        hash += (int) x;
        hash *= 17;
        hash = hash % 256;
        return hash;
    }

    public static class lens {

        String label;
        int value;

        lens (String label, int value) {
            this.label = label;
            this.value = value;
        }
    }

    public static boolean contains (lens[] tab, String label) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].label.equals(label)) {
                return true;
            }
        }
        return false;
    }

    public static lens[] replace (lens[] tab, String label, int value) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].label.equals(label)) {
                tab[i].value = value;
            }
        }
        return tab;
    }

    public static lens[] insert (lens[] tab, String label, int value) {
        lens[] res = new lens[tab.length+1];
        for (int i = 0; i < tab.length; i++) {
            res[i] = tab[i];
        }
        res[tab.length] = new lens(label, value);
        return res;
    }

    public static lens[] remove (lens[] tab, String label) {
        lens[] res = new lens[tab.length-1];
        int off = 0;

        for (int i = 0; i < tab.length; i++) {
            if (!tab[i].label.equals(label)) {
                res[i + off] = tab[i];
            } else {
                off = -1;
            }
        }
        return res;
    }

    public static lens[][] execute (String instr, lens[][] table) {
        String[] temp = instr.split(";");
        String label = temp[0];
        String instructionType = temp[1];
        
        

        int index = 0;

        for (int i = 0; i < label.length(); i++) {
            index = hashed(index, label.charAt(i));
        }

        

        if (instructionType.charAt(0) == '-') {
            if (contains(table[index], label)) {
                table[index] = remove(table[index], label);
                return table;
            } else {
                return table;
            }
        } else {
            int value;

            String valTemp = "";
            for (int i = 1; i < instructionType.length(); i++) {
                valTemp += instructionType.charAt(i);
            }
            
            value = Integer.valueOf(valTemp);

            if (contains(table[index], label)) {
                table[index] = replace(table[index], label, value);
                return table;
            } else {
                table[index] = insert(table[index], label, value);
                return table;
            }
        }
    }

    public static int res (lens[][] hashTable) {
        int res = 0;
        for (int i = 0; i < hashTable.length; i++) {
            for (int j = 0; j < hashTable[i].length; j++) {
                res += (i+1) * (j+1) * hashTable[i][j].value;
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String[] instructions = null;
            lens[][] hashTable = new lens[256][];
            for (int i = 0; i < hashTable.length; i++) {
                hashTable[i] = new lens[0];
            }
            

            while ((line = bufferedReader.readLine()) != null) {
                instructions = line.split(",");
            }

            int hash = 0;
            int res = 0;
            for (int i = 0; i < instructions.length; i++) {
                hashTable = execute(instructions[i], hashTable);
            }

            
            int res2 = res(hashTable);

            System.out.println(res);
            System.out.println(res2);
        }
    }
}
