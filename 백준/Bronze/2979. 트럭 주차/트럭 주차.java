import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] cost = {0, a, b, c};
        int[] parkingTime = new int[101];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int j = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            for (; j < length; j++) {
                parkingTime[j]++;
            }
        }

        for (int time : parkingTime) {
            answer += (cost[time] * time);
        }

        bw.write(answer+"\n");
        bw.flush();
        bw.close();
    }
}