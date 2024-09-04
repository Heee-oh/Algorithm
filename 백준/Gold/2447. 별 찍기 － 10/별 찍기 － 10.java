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

        for (char[] star : stars) {
            Arrays.fill(star, '*');
        }

        recursion(0, 0, n, n);

        for (char[] star : stars) {
            sb.append(star).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void recursion(int r, int c, int length, int n) {

        if (length == 1) {
            return;
        }

        int div = length / 3;


        for (int i = r; i < r +length; i += div) {
            for (int j = c; j < c + length; j += div) {
                recursion(i,j, div, n);
            }
        }

        for (int i = r + div; i < r + div + div; i++) {
            for (int j = c + div; j < c + div + div; j++) {
                stars[i][j] = ' ';
            }
        }




    }

}