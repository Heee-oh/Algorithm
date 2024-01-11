import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static boolean[] visited;
    public static int[][] graph;
    static int v,e;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int v1,v2;

        // 초기화 v+1 0번은 비우기
        graph = new int[v+1][v+1];
        visited = new boolean[v+1];

        for(int i = 1; i <= e; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());

            graph[v1][v2] = graph[v2][v1] = 1;

        }





        //DFS
        dfs(start);

        System.out.println();

        visited = new boolean[v+1];

        //BFS
        bfs(start);


    }

    public static void dfs(int nodeIndex) {
        visited[nodeIndex] = true;
        System.out.print(nodeIndex + " ");

        for(int next = 1; next < v+1; next++) {
            if(graph[nodeIndex][next] == 1 && !visited[next]) {
                dfs(next);
            }
        }



    }

    public static void bfs(int nodeIndex) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(nodeIndex);
        visited[nodeIndex] = true;

        while(!que.isEmpty()) {
            int currentNode = que.poll();
            System.out.print(currentNode + " ");

            for(int next = 1; next <= v; next++) {
                if(graph[currentNode][next] == 1 && !visited[next]) {
                    visited[next] = true;
                    que.offer(next);
                }
            }
        }
    }

}