import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day13 {

    public static int getSmugedRes (String[] mirror) {
        for (int i = 0; i < mirror.length; i++) {
            for (int j = 0; j < mirror[i].length(); j++) {
                String prevLine = mirror[i];
                String newLine = "";
                for (int k = 0; k < mirror[i].length(); k++) {
                    if (k == j) {
                        if (mirror[i].charAt(j) == '.') {
                            newLine += '#';
                        } else {
                            newLine += '.';
                        }
                    } else {
                        newLine += mirror[i].charAt(k);
                    }
                }

                int oldHoriz = getLineAboveHorizontaleReflexion(mirror, 0, -1);
                int oldVert = getLineAsideVerticalReflexion(mirror, 0, -1);

                mirror[i] = newLine;


                int res = (getLineAboveHorizontaleReflexion(mirror, 0, oldHoriz) * 100) + getLineAsideVerticalReflexion(mirror, 0, oldVert);
                
                mirror[i] = prevLine;

                if (res != 0) {
                    return res;
                }
            }
        }
        return 0;
    }


    public static boolean verticalLineEquals (String[] t, int l1, int l2) {
        for (int i = 0; i < t.length; i++) {
            if (t[i].charAt(l1) != t[i].charAt(l2)) {
                return false;
            }
        }
        return true;
    }

    public static int getLineAsideVerticalReflexion (String[] mirror, int start, int alreadyFound) {
        for (int i = start; i < mirror[0].length() - 1; i++) {
            boolean isReflexion = true;
            for (int j = i; j >= 0; j--) {
                if (i + (i - j) + 1 < mirror[0].length()) {
                    if (!verticalLineEquals(mirror, j, i + (i - j) + 1)) {
                        isReflexion = false;
                    }
                }
            }
            if (isReflexion && i + 1 != alreadyFound) {
                return i + 1 + getLineAsideVerticalReflexion(mirror, i + 1, alreadyFound);
            }
        }
        return 0;
    }

    public static int getLineAboveHorizontaleReflexion (String[] mirror, int start, int alreadyFound) {
        for (int i = start; i < mirror.length - 1; i++) {
            boolean isReflexion = true;
            for (int j = i; j >= 0; j--) {
                if (i + (i - j) + 1 < mirror.length) {
                    if (!mirror[j].equals(mirror[i + (i - j) + 1])) {
                        isReflexion = false;
                    }
                }
            }
            if (isReflexion && i + 1 != alreadyFound) {
                return i + 1 + getLineAboveHorizontaleReflexion(mirror, i + 1, alreadyFound);
            }
        }
        return 0;
    }

    public static String[] addLineMirror (String[] mirror, String line) {
        String[] res = new String[mirror.length+1];

        for (int i = 0; i < mirror.length; i++) {
            res[i] = mirror[i];
        }

        res[mirror.length] = line;
        return res;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String[] mirror = new String[0];
            String[][] mirrors = new String[100][];
            int l = 0;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("-")) {
                    mirrors[l++] = mirror;
                    mirror = new String[0];
                } else {
                    mirror = addLineMirror(mirror, line);
                }
            }
            mirrors[l++] = mirror;

            int res = 0;
            int res2 = 0;
            for (int i = 0; i < mirrors.length; i++) {
                res += (getLineAboveHorizontaleReflexion(mirrors[i], 0, -1) * 100) + getLineAsideVerticalReflexion(mirrors[i], 0, -1);
                res2 += getSmugedRes(mirrors[i]);
            }

            System.out.println(res);
            System.out.println(res2);
        }
    }
}
