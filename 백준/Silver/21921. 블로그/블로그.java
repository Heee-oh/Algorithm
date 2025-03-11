import java.io.*;
import java.util.*;

public class Main {
    // X일동안 최대 방문자수 구하기
    // 최대 방문자수가 0명이라면 SAD 출력, 아니라면 최대 방문자수인 기간 몇개인지 출력

    // 투포인터로 하면 될 듯

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 0; i < x; i++) {
            max += arr[i];
        }


        int front = 0, back = x;
        int cnt = 1;
        int sum = max;
        while (back < n) {

            sum = sum - arr[front++] + arr[back++];
            if (max < sum) {
                max = sum;
                cnt = 1;

            } else if (sum == max) {
                cnt++;
            }
        }

        if (max == 0) {
            System.out.println("SAD");
            return;
        }

        System.out.println(max);
        System.out.println(cnt);

    }
}
