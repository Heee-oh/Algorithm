import java.io.*;
import java.util.*;

public class Main {

    static Set<String> set = new HashSet<>(); // 수열 중복 체크
    static StringBuilder sb = new StringBuilder(); // 수열 만들기용
    static List<Integer> list = new ArrayList<>(); // m개 뽑은 수를 저장
    static Queue<String> q = new LinkedList<>(); // 답을 저장
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
        visited = new boolean[n];
        seq = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(seq);
        backtracking(0);

        while (!q.isEmpty()) {
            sb.append(q.poll()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }



    private static void backtracking(int start) {

        // 길이가 m이 됐다면
        if (list.size() == m) {

            // 수열 만들기
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i)).append(" ");
            }

            // 중복체크
            if (!set.contains(sb.toString())) {
                set.add(sb.toString());
                q.add(sb.toString());
            }
            sb.delete(0, sb.length()); // 임시로 만든 수열 지우기
            return;
        }

        //백트래킹
        for (int i = start; i < n; i++) {

            list.add(seq[i]);
            backtracking(i);
            list.remove(list.size() - 1);

        }
    }
}
