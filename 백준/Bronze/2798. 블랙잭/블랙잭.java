import java.io.*;
import java.util.*;

public class Main {

    static int max = 0;
    static int[] cards;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        cards = new int[n];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        calculateMaxCardSum(n - 1, m, 0, 0);


        bw.write(max + "");
        bw.flush();
        bw.close();
    }

    private static void calculateMaxCardSum(int start, int m, int count, int sum) {

        if (count == 3) {
            max = Math.max(sum, max);
            return;
        }

        for (int i = start; i >= 0; i--) {
            if (sum + cards[i] <= m ) {
                calculateMaxCardSum(i - 1, m, count +1, sum + cards[i]);
            }
        }
    }

}