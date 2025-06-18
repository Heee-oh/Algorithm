import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 1799번 비숍
    static int N;
    static int max = 0;

    // 잘한 점 : 왼쪽 대각, 오른쪽 대각에 번호 매겨서 1차원 배열로 방문체크
    // 못한 점 : 시간 복잡도를 너무 얕봤다. 배제할 건 최대한 배제해야하는데 또 게을러 터져서 대충푼거
    // 자꾸 쓸데없는곳에서 시간복잡도를 줄이려해서 문제를 더 어렵게 만듦 ex) 자주 참조하늬까 리스트는 static으로


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<int[]> black = new ArrayList<>();
        List<int[]> white = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                String s = st.nextToken();
                if (s.equals("0")) continue;

                // white
                if ((i + j) % 2 == 0) {
                    white.add(new int[]{i, j});
                    // black
                }else {
                    black.add(new int[]{i, j});
                }
            }
        }

        int whiteMax = dfs(white, 0, new boolean[2 * N - 1], new boolean[2 * N - 1]);
        int blackMax = dfs(black, 0, new boolean[2 * N - 1], new boolean[2 * N - 1]);
        System.out.println(whiteMax + blackMax);

    }


    private static int dfs(List<int[]> list, int idx, boolean[] rDiag, boolean[] lDiag) {
        if (idx == list.size()) return 0;

        int[] pos = list.get(idx);
        int r = pos[0], c = pos[1];
        int rd = r + c;
        int ld = r - c + N - 1;

        int take = 0;
        if (!rDiag[rd] && !lDiag[ld]) {
            rDiag[rd] = lDiag[ld] = true;
            take = 1 + dfs(list, idx + 1, rDiag, lDiag);
            rDiag[rd] = lDiag[ld] = false;
        }

        int skip = dfs(list, idx + 1, rDiag, lDiag);

        return Math.max(take, skip);
    }
}

