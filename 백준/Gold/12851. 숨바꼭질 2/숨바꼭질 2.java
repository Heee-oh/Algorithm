import java.io.*;
import java.util.*;

public class Main {

    static int[] visited = new int[100001];
    static int minTime = Integer.MAX_VALUE; // 가장 빠른 시간
    static int cnt; // 가짓수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n >= k) {
            System.out.println((n-k) + "\n1");
            return;
        }

        bfs(n, k);

        System.out.println(minTime + "\n" + cnt);

    }


    private static void bfs(int n, int k) {
        Queue<Integer> q = new LinkedList<>();

        q.add(n);
        visited[n] = 1;
        while (!q.isEmpty()) {
            int current = q.poll();

            if (visited[current] > minTime) return;


            for (int newX : move(current)) {

                if (newX < 0 || newX > 100000) continue;

                if (newX == k) {
                    minTime = visited[current];
                    cnt++;
                }

                if (visited[newX] == 0 || visited[newX] == visited[current] + 1) {
                    q.add(newX);
                    visited[newX] = visited[current] + 1;
                }
            }
        }
    }

    private static int[] move(int current) {
        int xPlus = current + 1;
        int xMinus = current - 1;
        int xMulti2 = current * 2;

        return new int[]{xPlus, xMinus, xMulti2};
    }


}