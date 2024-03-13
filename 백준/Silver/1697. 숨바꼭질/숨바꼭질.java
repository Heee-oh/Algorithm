import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int answer;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] graph = new int[100001];

        int x = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        graph[x] = 1;

        bfs(x, graph, k);
        bw.write(answer+"\n");
        bw.flush();
        bw.close();

    }

    private static void bfs(int x, int[] graph, int k) {
        Queue<Integer> q = new LinkedList<>();
        q.add(x);

        while (!q.isEmpty()) {
            int current = q.poll();
            int count = graph[current];

            if (current == k) {
                answer = count - 1;
                return;
            }

            if (current - 1 >= 0 && graph[current - 1] == 0) {
                graph[current - 1] = count + 1;
                q.add(current - 1);
            }
            if (current + 1 < graph.length && graph[current + 1] == 0) {
                graph[current + 1] = count + 1;
                q.add(current + 1);
            }
            if (current * 2 < graph.length && graph[current * 2] == 0) {
                graph[current * 2] = count + 1;
                q.add(current * 2);
            }
        }

    }
}
