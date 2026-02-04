import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dp;
    static int[] unhealthy;
    static int cnt = 0;
    static int K;
    static List<Integer>[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        unhealthy = new int[N + 1];
        tree = new List[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            unhealthy[i] = Integer.parseInt(st.nextToken());
            tree[i] = new ArrayList<>();

        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);

        }


        dfs(1, 0);
        System.out.println(cnt);


    }


    private static int dfs(int cur, int parent) {

        List<Integer> childCnt = new ArrayList<>();
        int curSum = unhealthy[cur];

        for (int i = 0; i < tree[cur].size(); i++) {
            int next = tree[cur].get(i);
            if (parent == next) continue;

            childCnt.add(dfs(next, cur));
        }

        Collections.sort(childCnt, Collections.reverseOrder());

        for (int val : childCnt) {
            curSum += val;
        }

        for (int val : childCnt) {
            if (curSum <= K) break;

            curSum -= val;
            cnt++;
        }

        return curSum;
    }


}
