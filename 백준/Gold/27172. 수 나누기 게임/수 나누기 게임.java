import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static final int MAX = 1000000;
    static int[] players;
    static int[] answer;
    static Map<Integer, Integer> idxMap = new HashMap<>();
    static int[] freq = new int[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        players = new int[n];
        answer = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            players[i] = Integer.parseInt(st.nextToken());
            idxMap.put(players[i], i);
            freq[players[i]]++;
        }

        for (int i = 1; i <= MAX; i++) {
            if (freq[i] == 0) continue;
            for (int j = i * 2; j <= MAX; j += i) {
                if (freq[j] > 0) {
                    answer[idxMap.get(i)] += freq[j];
                    answer[idxMap.get(j)] -= freq[i];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int val : answer) sb.append(val).append(" ");
        System.out.println(sb);
    }
}
