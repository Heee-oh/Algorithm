import java.io.*;
import java.util.*;

public class Main {
    static char[][] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        stars = new char[n][n];

        recursion(0,0,n,true);

        for (char[] star : stars) {
            sb.append(star).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void recursion(int r, int c, int length, boolean isStar) {

        if (length == 1) {
            if (isStar) {
                stars[r][c] = '*';
                return;
            }
        }

        int div = length / 3;

        if (isStar) {
            int count = 1;
            for (int i = r; i < r + length; i += div) {
                for (int j = c; j < c + length; j += div) {

                    // 가운데 빈 공간
                    if (count == 5) {
                        recursion(i, j, div, false);
                        count++;
                    } else {
                        recursion(i, j, div, true);
                        count++;
                    }
                }
            }
        } else {
            for (int i = r; i < r + length; i++) {
                for (int j = c; j < c + length; j++) {
                    stars[i][j] = ' ';
                }
            }
        }
    }



    //        if (a >= Math.sqrt(Integer.MAX_VALUE) || Math.pow(a, pow) > Integer.MAX_VALUE) {
//        if (a >= Math.sqrt(Integer.MAX_VALUE) && pow >= Math.sqrt(Integer.MAX_VALUE)) {
//            int odd = (pow / 2) + pow % 2 == 0 ? 1 : 0;
//            int even = pow / 2;
//
//            return (recursion(odd) * recursion(even)) % c;
//
//        } else {
//
//            long tmp = 1;
//            for (int i = 0; i < pow; i++) {
//                tmp *= a;
//                tmp %= c;
//            }
//
//            return tmp;
//        }

}