import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] countries = new int[n];
        int max = 0;


        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            countries[i] = Integer.parseInt(st.nextToken());
            max += countries[i];
        }

        int m = Integer.parseInt(br.readLine()); // 총 예산

        // 예산 이진 탐색
        int front = 0, back = Arrays.stream(countries).max().getAsInt();
        int answer = 0;

        while (front <= back) {

            int mid = (back + front) / 2;
            long tmp = 0;


            for (int i = 0; i < n; i++) {

                if (countries[i] <= mid) {
                    tmp += countries[i];
                } else {
                    tmp += mid;
                }

            }

            // 배정된 예산값이 국가 예산 총액을 넘어가거나,
            // 모든 요청의 합 보다 배정된 예산값이 크거나 같으면 값 범위를 줄임
            if (tmp > m) {
                back = mid - 1;


                // 배정된 예산값이 국가 예산 보다 적다면 더 큰 상한액을 찾기 위해 값 범위를 키움
            } else {
                if (max <= tmp) {
                    back = mid - 1;
                } else {
                    front = mid + 1;
                }

                answer = mid;
            }
        }


        bw.write(answer + "");
        bw.flush();
        bw.close();
    }
}