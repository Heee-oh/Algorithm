import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int[][] graph;
    static boolean[] visited;
    static int V,E,count;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        // 1부터 시작
        graph = new int[V + 1][V + 1];
        visited = new boolean[V+1];

        int v1,v2;

        // 그래프 초기화
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());

            graph[v1][v2] = graph[v2][v1] = 1;

        }

        dfs(1);

        bw.write(count + "");
        bw.flush();
        bw.close();
    }

    public static void dfs(int startN) {

        visited[startN] = true;

        for (int i = 1; i <= V; i++) {
            if (graph[startN][i] == 1 && !visited[i]) {
                count++;
                dfs(i);
            }

        }

    }

}


/**
 * graph[][] 1개 필요
 * visited[] 1개 필요
 * 그냥 dfs 사용하면 될 듯 싶다.
 *
 */