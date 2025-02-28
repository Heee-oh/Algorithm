import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        sequence[0] = Integer.parseInt(st.nextToken());
        int size = 1;

        for (int i = 1; i < n; i++) {
            int num =  Integer.parseInt(st.nextToken());
            int front = 0, back = size - 1;

            while (front <= back) {
                int mid = (front + back) / 2;

                // 수열에 mid 위치에 값이 num 보다 크다면
                if (sequence[mid] > num) {
                    back = mid - 1;

                } else if (sequence[mid] < num){
                    front = mid + 1;
                } else {
                    break;
                }

                if (front > back) {
                    if (sequence[mid] > num) {
                        sequence[mid] = num;

                    } else {
                        sequence[front] = num;
                        size += front == size ? 1 : 0;
                    }
                }
            }
        }

        bw.write(size + "");
        bw.flush();
        bw.close();
    }
}