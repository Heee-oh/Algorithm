import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    static boolean[] nums;
    static int[] players;
    static int[] sortedPlayers;
    static int max;
    static Map<Integer, Integer> idxMap = new HashMap<>();
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        players = new int[n];
        answer = new int[n];
        nums = new boolean[1000001];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            players[i] = Integer.parseInt(st.nextToken());
            idxMap.put(players[i], i); // 수에 대한 인덱스
            nums[players[i]] = true; // 플레이어의 수 존재 표시
        }

        sortedPlayers = players.clone();
        Arrays.sort(sortedPlayers);
        max = sortedPlayers[n-1];

        for (int i = 0; i < n; i++) {
            nums[sortedPlayers[i]] = false;
            sieveOfEratosthenes(i);
        }


        StringBuilder sb = new StringBuilder();
        Arrays.stream(answer).forEach(x -> sb.append(x).append(" "));
        System.out.println(sb.toString());

    }

    private static void sieveOfEratosthenes(int idx) {
        int tmp = sortedPlayers[idx];
        for (int i = 2; i <= max; i++) {
            int target = tmp * i;
            if (target > max) return;
            
            if (nums[target]) {
                int curIdx = idxMap.get(tmp);
                int targetIdx = idxMap.get(target);
                answer[curIdx]++;
                answer[targetIdx]--;
            }
        }
    }


}
