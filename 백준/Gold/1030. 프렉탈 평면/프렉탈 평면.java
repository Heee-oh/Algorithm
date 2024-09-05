import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
    static int s, n, k, r1,r2,c1,c2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 원하는 위치의 그림 크기 초기화


        s = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        graph = new int[r2 - r1 + 1][c2 - c1 + 1];

        int size = (int) Math.pow(n, s);

        // n x n 으로 분할
        // k x k 정사각형으로 검은색이 채워짐
        // 가운데가 검정

        recursion(0, 0, size,0, false);

        for (int[] ints : graph) {
            for (int n : ints) {
                sb.append(n);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    private static void recursion(int r, int c, int length, int second, boolean isBlack) {

        // 정해진 범위를 벗어낫 구역이면 넘기기
        if ((r < r1 && r + length <= r1) || (c < c1 && c + length <= c1) || r > r2 || c > c2 ) return;

        // 크기가 1이고 블랙이면 1 처리
        if (length == 1 && isBlack) {
            graph[r -r1][c -c1] = 1;
            return;
        }


        int div = length / n;

        int blackPoint = ((n - k) / 2) * div;
        int blackR = blackPoint + r;
        int blackC = blackPoint + c;


        if (second < s) {
            for (int i = r; i < r + length; i += div) {
                for (int j = c; j < c + length; j += div) {

                    // 중앙 블랙 처리
                    if (blackR == i && blackC == j) {
                        recursion(i, j, div, second + 1, true);
                    } else {
                        recursion(i, j, div, second + 1,  i >= blackR && j >= blackC && i < blackR + (k * div) && j < blackC + (k * div) || isBlack);
                    }
                }
            }
        }

    }


}