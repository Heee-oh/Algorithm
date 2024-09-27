import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    // 현재 삽입할 값을 미리 저장
                    int cur = arr[i];

                    // 뒤에서부터 땡겨온다.
                    for (int k = i; k > j; k--) {
                        arr[k] = arr[k - 1];
                    }
                    
                    // 알맞는 자리에 삽입
                    arr[j] = cur;
                }
            }
        }

        int sum = 0;
        int answer = 0;
        for (int i : arr) {
            sum += i;
            answer += sum;
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
    }


}
