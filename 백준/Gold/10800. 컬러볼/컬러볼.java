import java.io.*;
import java.util.*;

public class Main {

    static class Ball {
        int idx;
        int color;
        int size;

        public Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] answer = new int[N];
        Ball[] arr = new Ball[N]; // 각 색의 총 크기
        int[] colorSum = new int[N + 1];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            arr[i] = new Ball(i, c, s);
        }

        Arrays.sort(arr, (o1, o2) -> o1.size - o2.size);


        int total = 0; // 누적합
        int cursor = 0;
        for (int i = 0; i < N; i++) {
            while (cursor < i && arr[i].size > arr[cursor].size) {
                total += arr[cursor].size;
                colorSum[arr[cursor].color] += arr[cursor].size;
                cursor++;
            }

            answer[arr[i].idx] = total - colorSum[arr[i].color];
        }


        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.print(sb.toString().trim());
    }

}
