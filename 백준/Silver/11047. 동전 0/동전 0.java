import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int cnt = 0;
        int[] coinValue = new int[n];
        for(int i = 0; i < n; i ++) {
            coinValue[i] = Integer.parseInt(br.readLine());
        }
        

        for(int i = n - 1; i >= 0; i--) {
            while (k - coinValue[i] >= 0) {
                k -= coinValue[i];
                cnt++;

                if(k == 0)
                    break;

            }

        }
        bw.write(cnt + "\n");
        bw.flush();
        bw.close(); // 종료
    }
}