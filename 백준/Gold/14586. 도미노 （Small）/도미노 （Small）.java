import java.io.*;
import java.util.*;

public class Main {

    static class Domino {
        int x, h;
        long left, right;

        public Domino(int x, int h) {
            this.x = x;
            this.h = h;
            left = (long)x - h;
            right = (long)x + h;
        }
    }
    // xi ,hi 왼쪽 x-h ~ x, 오른쪽 x x+h
    // x ,h <= 20억, 계산시 long타입 필요
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Domino[] arr = new Domino[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            arr[i] = new Domino(x, h);
        }

        Arrays.sort(arr, (o1, o2) -> o1.x -o2.x);

        // 넘어뜨린 개수 , idx, (1오른쪽, -1 왼쪽)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);

        for (int i = 0; i < n; i++) {

            //오른쪽으로 쓰러뜨린 개수
            int current = i;
            int next = i+1;
            int cnt = 1;
            while (next < n) {
                if (arr[next].x <= arr[current].right) {
                    // 다음것이 right가 더 크다면 변경
                    if (arr[next].right > arr[current].right) {
                        current = next;
                    }
                    cnt++;
                    next++;
                } else {
                    break;
                }
            }

            pq.offer(new int[]{cnt, i, 1});

            cnt = 1;
            current = i;
            next = i-1;
            while (next >= 0) {
                if (arr[current].left <= arr[next].x) {

                    // 다음것이 left가 더 작다면 변경
                    if (arr[next].left < arr[current].left) {
                        current = next;
                    }
                    cnt++;
                    next--;

                }else {
                    break;
                }
            }

            pq.offer(new int[]{cnt, i, -1});
        }

        int answer = 0;
        Set<Integer> set = new HashSet<>();
        while (!pq.isEmpty()) {

            int[] domino = pq.poll();
            int cnt = domino[0];
            int idx = domino[1];
            int direction = domino[2];
            if (set.size() == n) {
                break;
            }

            if (direction == 1) {
                boolean flag = true;
                for (int i = idx; i < idx + cnt; i++) {
                    if (set.contains(i)) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) continue;

                for (int i = idx; i < idx + cnt; i++) {
                    set.add(i);
                }

                answer++;

            } else {
                boolean flag = true;
                for (int i = idx; i >= idx - cnt + 1; i--) {
                    if (set.contains(i)) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) continue;

                for (int i = idx; i >= idx - cnt  + 1; i--) {
                    set.add(i);
                }

                answer++;
            }
        }


        System.out.println(answer);

    }



}
