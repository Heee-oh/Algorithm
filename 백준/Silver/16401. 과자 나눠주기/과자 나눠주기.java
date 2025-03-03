import java.io.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] snacks = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
            max = Math.max(snacks[i], max);
        }

        int front = 1, back = max;
        int size = -1;
        while (front <= back) {

            int mid = (front + back) >>> 1;
            int count = 0;

            // 과자 나누기
            for (int snack : snacks) {
                count += snack / mid;
            }

            // 나눈 과자 개수가 조카 M명보다 많거나 같다면 과자길이를 더 늘림
            if (count >= m) {
                size = mid;
                front = mid + 1;
                // 나눈 과자 개수가 조카 M명보다 적다면 과자 길이를 줄임
            } else {
                back = mid - 1;
            }
        }

        System.out.println(size == -1 ? 0 : size);



    }



}