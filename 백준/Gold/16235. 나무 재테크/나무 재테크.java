import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] A, nutrients;
    static LinkedList<Integer>[][] trees;

    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];
        nutrients = new int[N + 1][N + 1];
        trees = new LinkedList[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                nutrients[i][j] = 5;
                trees[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees[r][c].add(age); // 초기에는 순서 상관없지만, 삽입 후 정렬
        }

        while (K-- > 0) {
            // 1. 봄 + 여름
            int[][] dead = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (trees[i][j].isEmpty()) continue;

                    LinkedList<Integer> newList = new LinkedList<>();
                    Collections.sort(trees[i][j]); // 매년 정렬 필수 (대신 빠름)

                    for (int age : trees[i][j]) {
                        if (nutrients[i][j] >= age) {
                            nutrients[i][j] -= age;
                            newList.add(age + 1);
                        } else {
                            dead[i][j] += age / 2;
                        }
                    }

                    trees[i][j] = newList;
                }
            }

            // 2. 여름: 죽은 나무 양분으로
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    nutrients[i][j] += dead[i][j];
                }
            }

            // 3. 가을: 번식
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    for (int age : trees[i][j]) {
                        if (age % 5 == 0) {
                            for (int d = 0; d < 8; d++) {
                                int nr = i + dr[d], nc = j + dc[d];
                                if (nr >= 1 && nr <= N && nc >= 1 && nc <= N) {
                                    trees[nr][nc].addFirst(1); // 어린 나무를 맨 앞에
                                }
                            }
                        }
                    }
                }
            }

            // 4. 겨울
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    nutrients[i][j] += A[i][j];
                }
            }
        }

        // 살아있는 나무 수 세기
        int result = 0;
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                result += trees[i][j].size();

        System.out.println(result);
    }
}
