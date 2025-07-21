import java.io.*;
import java.util.*;

public class Main {

    // 14889번 스타트와 링크

    static int n, max, min;
    static int[][] arr;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    static List<Integer> startTeam = new ArrayList<>();
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            set.add(i); // 미리 set에 모든 사람들 추가

            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        dfs(0);
        System.out.println(answer);
    }

    private static void dfs(int idx) {
        if (set.size() == n / 2) {
            List<Integer> linkTeam = new ArrayList<>(set); // 스타트팀에 뽑힌 사람을 제외한 나머지는 링크팀

            int linkTeamSum = 0;
            int startTeamSum = 0;
            for (int i = 0; i < linkTeam.size(); i++) {
                for (int j = i + 1; j < linkTeam.size(); j++) {
                    int link1 = linkTeam.get(i);
                    int link2 = linkTeam.get(j);

                    int start1 = startTeam.get(i);
                    int start2 = startTeam.get(j);

                    linkTeamSum += arr[link1][link2] + arr[link2][link1];
                    startTeamSum += arr[start1][start2] + arr[start2][start1];

                }
            }

            answer = Math.min(answer, Math.abs(startTeamSum - linkTeamSum));
            return;
        }

        for (int i = idx; i < n; i++) {
            startTeam.add(i);
            set.remove(i); // 스타트팀에 뽑힌 사람은 제거

            dfs(i + 1);

            startTeam.remove(startTeam.size() - 1);
            set.add(i); // 모든 경우의 수를 위해 다시 추가
        }

    }

}
