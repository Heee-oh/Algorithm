import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] countries = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            countries[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine()); // 총 예산

        // 이진 탐색을 위한 범위 설정
        int front = 0, back = Arrays.stream(countries).max().getAsInt();
        int answer = 0;

        while (front <= back) {
            int mid = (back + front) / 2;
            long tmp = 0;

            for (int i = 0; i < n; i++) {
                tmp += Math.min(countries[i], mid);
            }

            if (tmp > m) {
                back = mid - 1;
            } else {
                answer = mid; // 가능한 값 갱신
                front = mid + 1;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}
