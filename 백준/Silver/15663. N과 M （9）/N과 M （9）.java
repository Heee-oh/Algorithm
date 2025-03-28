import java.io.*;
import java.util.*;

public class Main {

    static Set<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int[] seq;
    static int n;
    static int m;
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
        backtracking();

        while (!q.isEmpty()) {
            sb.append(q.poll()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }


    static List<Integer> list = new ArrayList<>();
    static Queue<String> q = new LinkedList<>();
    private static void backtracking() {

        if (list.size() == m) {
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i)).append(" ");
            }

            if (!set.contains(sb.toString())) {
                set.add(sb.toString());
                q.add(sb.toString());
            }
            sb.delete(0, sb.length());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            list.add(seq[i]);
            visited[i] = true;
            backtracking();
            list.remove(list.size() - 1);
            visited[i] = false;

        }
    }

}
