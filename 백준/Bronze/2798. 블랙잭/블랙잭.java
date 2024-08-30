import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] cards = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        int answer = calculateMaxCardSum(n - 1, cards, m);


        bw.write(answer + "");
        bw.flush();
        bw.close();
    }

    private static int calculateMaxCardSum(int n, int[] cards, int m) {
        int sum = 0;
        int tmp = 0;
        // 3장의 합
        for (int i = n; i >= 2 ; i--) {
            sum = cards[i];
            for (int j = i - 1; j >= 1; j--) {
                if (sum + cards[j] > m) {
                    continue;
                }
                sum += cards[j];


                for (int k = j - 1; k >= 0; k--) {
                    if (sum + cards[k] <= m) {
                        tmp = Math.max(tmp, sum + cards[k]);
                    }
                }

                sum = cards[i];
            }
        }

        return tmp;
    }

}