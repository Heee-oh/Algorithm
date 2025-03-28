import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[] seq;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        seq = new int[n];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(seq);

        backtracking(0);

        System.out.print(answer.toString());
    }


    static List<Integer> list = new ArrayList<>();
    private static void backtracking(int depth) {
        if (depth == m) {
            for (int i = 0; i < list.size(); i++) {
                answer.append(list.get(i)).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            list.add(seq[i]);
            backtracking(depth+1);
            list.remove(list.size() - 1);
            visited[i] = false;

        }
    }
}
