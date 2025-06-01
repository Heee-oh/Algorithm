import java.io.*;
import java.util.*;

public class Main {

    static boolean[] visited;
    static boolean[] finished;
    static int[] students;

    static int assignedStudentCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine()); // test case

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            students = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            assignedStudentCnt = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

            bw.write((n - assignedStudentCnt) + "\n");

        }
        bw.flush();
        bw.close();
    }


    private static void dfs(int current) {
        visited[current] = true;
        int next = students[current];
        if (!visited[next]) {
            dfs(next);

        } else {
            if (!finished[next]) {
                // 이미 팀 구성을 한 학생이면 더이상 탐색 X
                for (int i = next; i != current; i = students[i]) {
                    assignedStudentCnt++;
                }
                assignedStudentCnt++;
            }
        }

        finished[current] = true; // 여기까지 탐색한 모든 학생들은 팀구성 시도를 했다고 표시
    }
}
