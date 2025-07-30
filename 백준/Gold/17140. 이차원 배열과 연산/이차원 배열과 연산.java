import java.io.*;
import java.util.*;

public class Main {

    // 16235번 나무 재태크
    // K년이 지난 후 살아남은 나무의 '수'를 출력
    static int r, c, k;
    static int MAXROW = 0, MAXCOL = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        // 행 개수 >= 열  : R 연산
        // 행 개수 <  열  : C 연산
        int[][] arr = new int[3][3];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if ( r < 3 && c < 3
                && arr[r][c] == k) {
            System.out.println(0);
            return;
        }

        for (int T = 1; T <= 100; T++) {

            // R 연산
            if (arr.length >= arr[0].length) {
                PriorityQueue<int[]>[] pq = new PriorityQueue[arr.length];

                for (int i = 0; i < arr.length; i++) {
                    Map<Integer, Integer> map = new HashMap<>();

                    pq[i] = new PriorityQueue<>((o1, o2) -> {
                        if (o1[1] == o2[1]) return o1[0] - o2[0];
                        return o1[1] - o2[1];
                    });

                    for (int j = 0; j < arr[0].length; j++) {
                        map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
                    }

                    for (int key : map.keySet()) {
                        if (key != 0) {
                            pq[i].offer(new int[]{key, map.get(key)});
                        }
                    }
                }

                int maxSize = 0;
                for (int i = 0; i < pq.length; i++) {
                    maxSize = Math.max(maxSize, pq[i].size());
                }

                MAXCOL = Math.min(maxSize * 2, 100);
                arr = new int[pq.length][MAXCOL];

                for (int i = 0; i < pq.length; i++) {
                    int col = 0;

                    while (!pq[i].isEmpty() && col < MAXCOL) {
                        int[] poll = pq[i].poll();
                        arr[i][col++] = poll[0];
                        arr[i][col++] = poll[1];
                    }
                }

                // C 연산
            } else {

                PriorityQueue<int[]>[] pq = new PriorityQueue[arr[0].length];
                int maxSize = 0;

                for (int col = 0; col < arr[0].length; col++) {
                    Map<Integer, Integer> map = new HashMap<>();

                    pq[col] = new PriorityQueue<>((o1, o2) -> {
                        if (o1[1] == o2[1]) return o1[0] - o2[0];
                        return o1[1] - o2[1];
                    });

                    for (int row = 0; row < arr.length; row++) {
                        map.put(arr[row][col], map.getOrDefault(arr[row][col], 0) + 1);
                    }

                    for (int key : map.keySet()) {
                        if (key != 0) {
                            pq[col].offer(new int[]{key, map.get(key)});
                        }
                    }

                    maxSize = Math.max(maxSize, pq[col].size());
                }


                MAXROW = Math.min(maxSize * 2, 100);
                arr = new int[MAXROW][pq.length];

                for (int i = 0; i < pq.length; i++) {
                    int row = 0;

                    while (!pq[i].isEmpty() && row < MAXROW) {
                        int[] poll = pq[i].poll();

                        arr[row++][i] = poll[0];
                        arr[row++][i] = poll[1];
                    }
                }
            }

            if (r  < arr.length
                    && c < arr[0].length
                    && arr[r][c] == k) {
                System.out.println(T);
                return;
            }
        }


        System.out.println(-1);



    }
}
