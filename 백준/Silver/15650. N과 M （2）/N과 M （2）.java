import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        visited = new boolean[n];
        dfs(n, m, 0, 0);

    }

    private static void dfs(int n, int m, int depth, int pre) {
        if (depth == m) {
            for (int node : arr) {
                System.out.print(node + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (pre > i) {
                    continue;
                }
                visited[i] = true;
                arr[depth] = i + 1;
                dfs(n,m,depth + 1, i+1);
                visited[i] = false;



            }
        }
    }


}