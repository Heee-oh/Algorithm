import java.io.*;
import java.util.*;

public class Main {

    // 24884번 장작 넣기
    // T시간동안  최소 K개 유지

    static class Node {
        int SKH;
        int[] arr;
        int time;

        public Node(int firewoodNum, int[] arr, int time) {
            this.SKH = firewoodNum;
            this.arr = arr;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken()); // 모닥불 개수 <= 6
        int W = Integer.parseInt(st.nextToken()) + 1; // SKH 시작 모닥불 번호 1 ~ N
        int T = Integer.parseInt(st.nextToken()); // 모닥불 놀이 시간 <= 11
        int K = Integer.parseInt(st.nextToken()); // 최소 모닥불 개수   <= 6


        int[] arr = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(W, arr.clone(), 0));

        int answer = 0;

        while (!q.isEmpty()) {
            Node fire = q.poll();
            int[] fireArr = fire.arr;
            fire.time++;

            // 화력 감소
            int[] old = fireArr.clone();
            int[] next = new int[old.length];
            for (int i = 1; i <= N; i++) {
                if (fire.time > 1 && fire.SKH == i) {
                    next[i] = old[i];
                } else {
                    int cnt = (old[i-1] > 0 ? 1 : 0) + (old[i+1] > 0 ? 1 : 0);
                    next[i] = old[i] - (3 - cnt);
                }
            }
            fireArr = next;

            // T시각에는 이동하지 않고 장작도 안넣음, 화력감소는 됨
            if (fire.time == T) {
                int cnt = 0;

                for (int i = 1; i < fireArr.length - 1; i++) {
                    if (fireArr[i] > 0) {
                        cnt++;
                    }
                }

                // K개 이상이면 경우의 수 1 개 추가
                if (cnt >= K) {
                    answer++;
                }
                continue;
            }

            // 장작 넣을 위치 탐색
            for (int i = fire.SKH - 1; i <= fire.SKH + 1; i++) {
                if (i < 1 || i > N ) continue;

                q.offer(new Node(i, fireArr.clone(), fire.time));
            }
        }

        System.out.println(answer);
    }

}
