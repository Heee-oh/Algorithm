import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int[] openSeq;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int open1 = Integer.parseInt(st.nextToken());
        int open2 = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());

        openSeq = new int[m];
        for (int i = 0; i < m; i++) {
            openSeq[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(dfs(0, open1, open2));
    }


    private static int dfs(int next, int open1, int open2) {
        if (next == m) return 0;

        return Math.min(Math.abs(openSeq[next] - open1) + dfs(next + 1, openSeq[next], open2),
                Math.abs(openSeq[next] - open2)+ dfs(next + 1, open1, openSeq[next]));
    }

}
