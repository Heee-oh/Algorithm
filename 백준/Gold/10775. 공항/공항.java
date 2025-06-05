import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static boolean[] visited;
    static int[] targetedGates;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        visited = new boolean[G + 1];
        targetedGates = IntStream.range(0, G + 1).toArray();
        int cnt = 0;

        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());

            int result = dfs(g);

            if (result == -1) break;
            else cnt++;
        }

        System.out.println(cnt);
    }

    private static int dfs(int gate) {
        if (targetedGates[gate] == 0) return -1;

        // 방문 하지 않았으면 방문 처리후 게이트 이동
        if (!visited[gate]) {
            visited[gate] = true;
            targetedGates[gate]--;
            return targetedGates[gate];
        }

        return targetedGates[gate] = dfs(targetedGates[gate]);
    }


}
