import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
//
//    static int[] dp;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];


        dfs(n,m,"");




    }
    // 처음값 0, m
    private static void dfs(int n, int m, String pre) {

        if (m == 0) {
            System.out.println(pre);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(n, m - 1, pre + i + " ");
                visited[i] = false;
            }

        }





    }

    // 이전 값 보다 작으면서
    // 음수가 아니게끔


}