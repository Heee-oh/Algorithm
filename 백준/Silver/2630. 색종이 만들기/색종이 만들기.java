import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static int blue, white;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        // 맵 초기화
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        recursion(0, 0, n, map[0][0]);



        bw.write(white + "\n");
        bw.write(blue + "\n");
        bw.flush();
        bw.close();
    }


    private static void recursion(int r, int c, int size, int isBlue) {
        if (size  < 1) return;

        int divSize = size / 2;
        int count = 0;
        boolean flag = false;

        for (int i = r; i < r+size; i++) {
            for (int j = c; j < c+size; j++) {
                if (isBlue != map[i][j]) {
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }

        if (flag) {
            recursion(r, c, divSize, map[r][c]);
            recursion(r, c + divSize, divSize, map[r][c + divSize]);
            recursion(r + divSize, c, divSize, map[r + divSize][c]);
            recursion(r + divSize, c + divSize, divSize, map[r + divSize][c + divSize]);

        } else {
            count++;
        }


        if (isBlue == 1) {
            blue += count;
        } else {
            white += count;
        }

    }
}