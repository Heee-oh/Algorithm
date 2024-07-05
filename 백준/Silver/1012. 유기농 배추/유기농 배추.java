import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static boolean visited[][];
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스 개수
        int t = Integer.parseInt(br.readLine());

        while (t-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            int[][] land = new int[row][col];
            visited = new boolean[row][col];

            // 땅 초기화
            for (int i = 0; i < cnt; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                land[r][c] = 1;
            }
            
            int count = 0;
            for (int i = 0; i < land.length; i++) {
                for (int j = 0; j < land[0].length; j++) {
                    if (!visited[i][j] && land[i][j] == 1) {
                        dfs(land, i, j);


                        count++;
                    }
                }
            }

            //dfs로 가자
            System.out.println(count);
        }

    }
    private static void dfs(int[][] land, int r, int c) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{r, c});

        while(!stack.isEmpty()) {

            int[] location = stack.pop();
            visited[location[0]][location[1]] = true;

            for (int i = 0; i < 4; i++) {
                int newRow = location[0] + dy[i];
                int newCow = location[1] + dx[i];

                if (newRow >= 0 && newRow < land.length
                    && newCow >= 0 && newCow < land[0].length) {
                    if (!visited[newRow][newCow] && land[newRow][newCow] == 1) {
                        stack.push(new int[] {newRow, newCow});

                    }
                }
            }
        }

    }
}

